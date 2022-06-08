package com.example.app_weather.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.app_weather.entity.enums.PermissionEnum;
import com.example.app_weather.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)

    @Enumerated(EnumType.STRING)
    private List<PermissionEnum> permissionEnum;

    public Role(RoleEnum roleName, List<PermissionEnum> permissionEnum) {
        this.roleName = roleName;
        this.permissionEnum = permissionEnum;
    }

    public Role(Integer id, RoleEnum roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}
