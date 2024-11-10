package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Owner;

/**
 * Repositorio para la entidad Owner.
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    // Buscar propietarios por nombre
    List<Owner> findByFirstName(String firstName);

    // Buscar propietarios por apellido
    List<Owner> findByLastName(String lastName);

    // Buscar propietarios por ciudad
    List<Owner> findByCity(String city);

    @Override
    List<Owner> findAll();
}
