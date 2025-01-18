package com.famtwen.identity_service.configuration;

import com.famtwen.identity_service.entity.Permission;
import com.famtwen.identity_service.entity.Role;
import com.famtwen.identity_service.entity.User;
import com.famtwen.identity_service.repository.PermissionRepository;
import com.famtwen.identity_service.repository.RoleRepository;
import com.famtwen.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnProperty(prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        log.info("========== INIT APPLICATION......");
        return args -> {
            if (userRepository.findByUsername("admin")
                              .isEmpty()) {
                createPermissions(permissionRepository);
                Role adminRole = createAdminRole(roleRepository, permissionRepository);
                Role userRole = createUserRole(roleRepository, permissionRepository);
                createAdminAccount(userRepository, adminRole);
            }
        };
    }

    private void createPermissions(PermissionRepository permissionRepository) {
        log.info("Creating permissions...");
        addPermissions(permissionRepository, "APPROVE_POST", "Approve a post");
        addPermissions(permissionRepository, "GET_DATA", "Get data");
        addPermissions(permissionRepository, "REJECT_POST", "Reject a post");
        addPermissions(permissionRepository, "UPDATE_DATA", "Update data");
    }

    private Permission addPermissions(PermissionRepository permissionRepository, String name, String description) {
        return permissionRepository
                .findByName(name)
                .orElseGet(() -> {
                    Permission permission = new Permission();
                    permission.setName(name);
                    permission.setDescription(description);
                    return permissionRepository.save(permission);
                });
    }

    private Role createAdminRole(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        log.info("Creating admin role...");
        return roleRepository
                .findByName(com.famtwen.identity_service.enums.Role.ADMIN.name())
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(com.famtwen.identity_service.enums.Role.ADMIN.name());
                    role.setDescription("Administrator role");
                    role.setPermissions(Set.of(
                            permissionRepository.findByName("APPROVE_POST")
                                                .orElseThrow(),
                            permissionRepository.findByName("UPDATE_DATA")
                                                .orElseThrow(),
                            permissionRepository.findByName("REJECT_POST")
                                                .orElseThrow()
                    ));
                    return roleRepository.save(role);
                });
    }

    private Role createUserRole(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        log.info("Creating user role...");
        return roleRepository
                .findByName(com.famtwen.identity_service.enums.Role.USER.name())
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(com.famtwen.identity_service.enums.Role.USER.name());
                    role.setDescription("User role");
                    role.setPermissions(Set.of(
                            permissionRepository.findByName("GET_DATA")
                                                .orElseThrow()
                    ));
                    return roleRepository.save(role);
                });
    }

    private void createAdminAccount(UserRepository userRepository, Role adminRole) {
        log.info("Creating Admin account...");

        User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .firstName("Administrator")
                        .lastName("Company Inc")
                        .dob(LocalDate.now()
                                      .minus(21, ChronoUnit.YEARS))
                        .roles(Set.of(adminRole))
                        .build();

        userRepository.save(user);

        log.warn("Admin account has been created with default password: admin. Please change it immediately!");
    }
}
