package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Representa a los propietarios en la aplicación.
 */
@Entity(name = "owners")
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;

    public Owner() {
    }

    public Owner(Integer id, String firstName, String lastName, String address, String city, String telephone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    public Owner(String firstName, String lastName, String address, String city, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    public String getName() {
        return firstName;
    }
}