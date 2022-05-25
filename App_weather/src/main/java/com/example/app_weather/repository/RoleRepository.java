package com.example.app_weather.repository;


import com.example.app_weather.entity.Role;
import com.example.app_weather.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleEnum name);

    List<Role> findAllById(String roleList);
}
