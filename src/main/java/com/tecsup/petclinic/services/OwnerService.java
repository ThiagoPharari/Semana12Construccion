package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

/**
 * Servicio para la entidad Owner.
 */
public interface OwnerService {

    /**
     * Crea un nuevo propietario.
     * @param owner
     * @return el propietario creado
     */
    Owner create(Owner owner);

    /**
     * Actualiza un propietario existente.
     * @param owner
     * @return el propietario actualizado
     */
    Owner update(Owner owner);

    /**
     * Elimina un propietario por ID.
     * @param id
     * @throws OwnerNotFoundException si el propietario no existe
     */
    void delete(Integer id) throws OwnerNotFoundException;

    /**
     * Busca un propietario por ID.
     * @param id
     * @return el propietario encontrado
     * @throws OwnerNotFoundException si el propietario no existe
     */
    Owner findById(Integer id) throws OwnerNotFoundException;

    /**
     * Busca propietarios por nombre.
     * @param firstName
     * @return lista de propietarios encontrados
     */
    List<Owner> findByFirstName(String firstName);

    /**
     * Busca propietarios por apellido.
     * @param lastName
     * @return lista de propietarios encontrados
     */
    List<Owner> findByLastName(String lastName);

    /**
     * Busca propietarios por ciudad.
     * @param city
     * @return lista de propietarios encontrados
     */
    List<Owner> findByCity(String city);

    /**
     * Obtiene todos los propietarios.
     * @return lista de todos los propietarios
     */
    List<Owner> findAll();
}