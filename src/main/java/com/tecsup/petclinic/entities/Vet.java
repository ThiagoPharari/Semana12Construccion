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


    public Vet() {
    }

    public Vet(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Vet(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
