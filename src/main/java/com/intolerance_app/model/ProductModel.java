package com.intolerance_app.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.intolerance_app.enums.HistamineLevelEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "product_intolerance",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "intolerance_id")
    )
    private Set<IntoleranceModel> intolerances;
}

