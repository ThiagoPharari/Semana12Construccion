package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;

import java.util.List;

/**
 * Service interface for Specialty
 *
 * @Author jgomezm
 */
public interface SpecialtyService {

    Specialty create(Specialty specialty);

    Specialty update(Specialty specialty);

    void delete(Integer id) throws SpecialtyNotFoundException;

    Specialty findById(Integer id) throws SpecialtyNotFoundException;

    List<Specialty> findByName(String name);

    List<Specialty> findAll();
}
