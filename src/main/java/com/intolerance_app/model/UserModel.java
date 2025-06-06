package com.intolerance_app.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", columnDefinition = "TEXT", nullable = false)
    private String username;

    @Column(name = "VORNAME", length = 128)
    private String userVorname;

    @Column(name = "NACHNAME", length = 128)
    private String userNachname;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

}
