package com.famtwen.identity_service.configuration;

import com.famtwen.identity_service.entity.User;
import com.famtwen.identity_service.enums.Role;
import com.famtwen.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository)
    {
        return args -> {
          if(userRepository.findByUsername("adminapp").isEmpty()){
              var roles = new HashSet<String>();
              roles.add(Role.ADMIN.name());

              User user =  User.builder()
                      .username("adminapp")
                      .password(passwordEncoder.encode("adminapp"))
                      .roles(roles)
                      .build();
              userRepository.save(user);
              log.warn("admin account has been created with default password: adminapp, please change it!!!");
          }
        };
    }
}