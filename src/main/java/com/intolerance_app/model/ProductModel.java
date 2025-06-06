package com.intolerance_app.model;

import com.intolerance_app.enums.HistamineLevelEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NAME", length = 128)
    private String productName;

    @Column(name = "DESCRIPTION")
    private String productDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "HISTAMINE_LEVEL")
    private HistamineLevelEnum histamineLevelEnum;

    @ManyToOne(optional = true)  // lub usuń nullable=false
    @JoinColumn(name = "user_id", nullable = true) // nullable = true oznacza, że kolumna może być pusta
    private UserModel user;

}

