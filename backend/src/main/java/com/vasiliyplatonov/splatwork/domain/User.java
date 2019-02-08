package com.vasiliyplatonov.splatwork.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String password;
    private List<Role> roles;
    private boolean enabled;
}
