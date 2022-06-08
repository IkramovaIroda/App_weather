package com.example.app_weather.controller;

import com.example.app_weather.dto.ApiResponse;
import com.example.app_weather.dto.CompanyDto;
import com.example.app_weather.entity.Company;
import com.example.app_weather.repository.CompanyRepository;
import com.example.app_weather.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    final CompanyRepository companyRepository;
    final CompanyService companyService;


    @GetMapping
    public HttpEntity<?> getAll(){
        System.out.println(companyRepository.getAll());
        return ResponseEntity.ok().body(companyRepository.getAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalCompany.get());
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse=companyService.add(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }


    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse=companyService.edit(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse=companyService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}
