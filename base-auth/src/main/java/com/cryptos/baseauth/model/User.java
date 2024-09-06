package com.cryptos.baseauth.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

//@Entity
@Data
@NoArgsConstructor
public class User {

    public User(String username, String email, String password, String authority) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

//    @Id
//    private int id;
    private String username;
    private String email;
    private String password;
    private String authority;
}
