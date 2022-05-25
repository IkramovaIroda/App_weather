package com.example.app_weather.service;

import com.example.app_weather.dto.ApiResponse;
import com.example.app_weather.dto.UserDTO;
import com.example.app_weather.entity.Role;
import com.example.app_weather.entity.Turniket;
import com.example.app_weather.entity.User;
import com.example.app_weather.entity.enums.RoleEnum;
import com.example.app_weather.repository.RoleRepository;
import com.example.app_weather.repository.TurniketRepository;
import com.example.app_weather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final TurniketRepository turniketRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public ApiResponse add(UserDTO dto) {
        String role = dto.getRole();
        RoleEnum roleEnum = RoleEnum.valueOf(role);
        //kim qoshilyapti user moderator
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        for (Role role1 : principal.getRoleList()) {
            //director
            if (role1.getRoleName().equals(RoleEnum.ROLE_ADMIN)) {
                //admin ekan moderator va user qo'sha olishi kerak
                if ( roleEnum.equals(RoleEnum.ROLE_USER)) {
                    User user = new User();
                    user.setUsername(dto.getUsername());
                    user.setPassword(passwordEncoder.encode("1111"));

                    Optional<Role> byRoleName = roleRepository.findByRoleName(roleEnum);
                    user.setRoleList(new LinkedHashSet(Collections.singleton(byRoleName.get())));

                    User save = userRepository.save(user);

                    Turniket turniket = new Turniket();
                    turniket.setUser(save);
                    turniketRepository.save(turniket);
                }
            }
//            } else {
//                throw new ResourceNotFoundException("Szda bunaqa huquq yoq");
//            }
        }
        return ApiResponse.builder().message("Added!").success(true).build();
    }

    public ApiResponse edit(Integer id, UserDTO dto) {
        List<Role> roles = roleRepository.findAllById(dto.getRole());
        if (roles.isEmpty()) return new ApiResponse("Roles not found", false);

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) return new ApiResponse("User not found", false);

        User user = optionalUser.get();

        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoleList(new LinkedHashSet(Collections.singleton(dto.getRole())));

        userRepository.save(user);

        return new ApiResponse("Edited", true);
    }
}
