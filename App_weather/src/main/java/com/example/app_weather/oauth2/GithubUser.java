package com.example.app_weather.oauth2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GithubUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    @OneToOne
//    private OAuth2User auth2User;

//    public GithubUser(OAuth2User auth2User) {
//        this.auth2User = auth2User;
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return auth2User.getAttributes();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return auth2User.getAuthorities();
//    }
//
//    @Override
//    public String getName() {
//        return auth2User.getName();
//    }

}
