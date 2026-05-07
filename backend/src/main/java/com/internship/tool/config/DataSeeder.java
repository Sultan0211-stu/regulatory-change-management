package com.internship.tool.config;

import com.internship.tool.entity.User;
import com.internship.tool.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            admin.setCreatedAt(LocalDateTime.now());
            userRepository.save(admin);
            log.info("Default admin user created — username: admin, password: admin123");
        }

        if (userRepository.findByUsername("manager").isEmpty()) {
            User manager = new User();
            manager.setUsername("manager");
            manager.setEmail("manager@example.com");
            manager.setPassword(passwordEncoder.encode("manager123"));
            manager.setRole("MANAGER");
            manager.setCreatedAt(LocalDateTime.now());
            userRepository.save(manager);
            log.info("Default manager user created — username: manager, password: manager123");
        }

        if (userRepository.findByUsername("viewer").isEmpty()) {
            User viewer = new User();
            viewer.setUsername("viewer");
            viewer.setEmail("viewer@example.com");
            viewer.setPassword(passwordEncoder.encode("viewer123"));
            viewer.setRole("VIEWER");
            viewer.setCreatedAt(LocalDateTime.now());
            userRepository.save(viewer);
            log.info("Default viewer user created — username: viewer, password: viewer123");
        }
    }
}
