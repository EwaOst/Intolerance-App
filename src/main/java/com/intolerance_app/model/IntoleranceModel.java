package com.intolerance_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany(mappedBy = "intolerances")
    @JsonIgnore
    private Set<UserModel> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntoleranceModel)) return false;
        IntoleranceModel other = (IntoleranceModel) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
