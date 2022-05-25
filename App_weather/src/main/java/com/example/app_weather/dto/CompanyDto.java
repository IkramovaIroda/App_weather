package com.example.app_weather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto {
//    @Min(value = 3)
//    @NotBlank
    private String name;
//    @NotNull
    private String username, email;
}
