package com.famtwen.identity_service.service;

import com.famtwen.identity_service.dto.request.AuthenticationRequest;
import com.famtwen.identity_service.dto.response.AuthenticationResponse;
import com.famtwen.identity_service.exception.AppException;
import com.famtwen.identity_service.exception.ErrorCode;
import com.famtwen.identity_service.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    protected static final String SIGNER_KEY = "XWl33AU6HWMautGS26y9CbGCIigErNizJnufWoFGEjvjUaIJBjmen2ggppRMM8Hy";
    // Đánh dấu để non final ko inject vào constructor


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //Đối số 10 là độ khó của thuật toán, xác định số vòng lặp của thuật toán Bcrypt.
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        //Kiểm tra pass người dùng nhập vào từ request có match với pass đã lưu.
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        //Kiểm tra nếu mật khẩu không đúng:
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        //Tạo token JWT:
        var token = generateToken(request.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(String username){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("famtwen.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customClaim", "Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes())); // Sử dụng Khóa đối xứng (Symmetric Key)

            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }
}
