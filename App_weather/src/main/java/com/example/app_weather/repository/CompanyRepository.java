package com.example.app_weather.repository;


import com.example.app_weather.dto.UserProjection;
import com.example.app_weather.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "select * from users",
            nativeQuery = true)
    List<UserProjection> getAll();


}
