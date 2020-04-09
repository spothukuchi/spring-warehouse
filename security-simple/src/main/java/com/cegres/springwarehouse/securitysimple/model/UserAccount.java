package com.cegres.springwarehouse.securitysimple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    Long id;

    String username;

    String password;

    boolean active;

    public UserAccount(String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }
}
