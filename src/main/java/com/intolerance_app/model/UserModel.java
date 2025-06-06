package com.intolerance_app.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "APP_USER")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN_NAME", columnDefinition = "TEXT", nullable = false)
    private String login_name;

    @Column(name = "USER_VORNAME", length = 128)
    private String user_vorname;

    @Column(name = "SURNAME", length = 128)
    private String userSurname;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductModel> products = new ArrayList<>();

}
