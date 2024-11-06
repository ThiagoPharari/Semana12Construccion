package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

/**
 *
 * @author jgomezm
 *
 */
@Entity(name = "vets")
@Data
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    // Relación Many-to-Many con Specialty
    @ManyToMany
    @JoinTable(
            name = "vet_specialties",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties;

    public Vet() {
    }

    public Vet(Integer id, String firstName, String lastName, Set<Specialty> specialties) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialties = specialties;
    }

    public Vet(String firstName, String lastName, Set<Specialty> specialties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialties = specialties;
    }
}
