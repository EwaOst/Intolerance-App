package com.intolerance_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INTOLERANCE")
public class IntoleranceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "INTOLERANCE_NAME", length = 128)
    private String intoleranceName;

    @Column(name = "DESCRIPTION")
    private String intoleranceDescription;

    // Relacja z użytkownikami
    @ManyToMany(mappedBy = "intolerances")
    private Set<UserModel> users;

    // Relacja z produktami
    @ManyToMany(mappedBy = "intolerances")
    private Set<ProductModel> products;

}
