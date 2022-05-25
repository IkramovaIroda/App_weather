package com.example.app_weather.service;

import com.example.app_weather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.System.*;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        out.println("loadUserByUsername called");
        return userRepository.findByUsername(username);
    }
}
