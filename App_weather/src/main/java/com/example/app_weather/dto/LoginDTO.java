package com.example.app_weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    //    @NotEmpty  //
//    @NotBlank //
//    @Email(regexp = "(.+)@gmail\\.com$", message = "Email emas!")
//    @Email
    //String lar un

    private String name;


    //field berilmasayam bo'sh berilsayam ishladi

//    @Pattern(regexp = "\\+998[0-9]{2}", message = "Topolmadim!")
    private String password;
}
