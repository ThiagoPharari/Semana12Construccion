package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad Specialty
 *
 */
@Entity(name = "specialties")
@Data
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Specialty() {
    }

    public Specialty(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Specialty(String name) {
        this.name = name;
    }
}
