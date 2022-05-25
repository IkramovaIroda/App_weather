package com.example.app_weather.repository;


import com.example.app_weather.entity.TurniketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurniketHistoryRepository extends JpaRepository<TurniketHistory, Integer> {
}
