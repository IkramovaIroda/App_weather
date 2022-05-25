package com.example.app_weather.component;

import com.example.app_weather.entity.Company;
import com.example.app_weather.entity.Role;
import com.example.app_weather.entity.User;
import com.example.app_weather.entity.enums.PermissionEnum;
import com.example.app_weather.entity.enums.RoleEnum;
import com.example.app_weather.repository.CompanyRepository;
import com.example.app_weather.repository.RoleRepository;
import com.example.app_weather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    //field metod
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;
    @Value("${spring.sql.init.mode}")

    String mode;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            PermissionEnum[] values = PermissionEnum.values();

            Set<Role> roleList = new LinkedHashSet<>();
            Role userRole = roleRepository.save(new Role(RoleEnum.ROLE_USER, new ArrayList<>(Arrays.asList(PermissionEnum.COMPANY_READ))));
            Role adminRole = roleRepository.save(new Role(RoleEnum.ROLE_ADMIN, Arrays.asList(values)));
            roleList.add(userRole);
            roleList.add(adminRole);

            User Iroda = userRepository.save(new User("Abdumajid", passwordEncoder.encode("1234"), roleList));
            companyRepository.save(new Company(1l, "PDP", Iroda));
        }
    }
}
