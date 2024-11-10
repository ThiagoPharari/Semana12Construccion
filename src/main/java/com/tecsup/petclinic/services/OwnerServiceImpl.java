package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;

/**
 * Implementación del servicio para la entidad Owner.
 */
@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Crea un nuevo propietario.
     * @param owner
     * @return el propietario creado
     */
    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     * Actualiza un propietario existente.
     * @param owner
     * @return el propietario actualizado
     */
    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     * Elimina un propietario por ID.
     * @param id
     * @throws OwnerNotFoundException si el propietario no existe
     */
    @Override
    public void delete(Integer id) throws OwnerNotFoundException {
        Owner owner = findById(id);
        ownerRepository.delete(owner);
    }

    /**
     * Busca un propietario por ID.
     * @param id
     * @return el propietario encontrado
     * @throws OwnerNotFoundException si el propietario no existe
     */
    @Override
    public Owner findById(Integer id) throws OwnerNotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (!owner.isPresent()) {
            throw new OwnerNotFoundException("Record not found...!");
        }
        return owner.get();
    }

    /**
     * Busca propietarios por nombre.
     * @param firstName
     * @return lista de propietarios encontrados
     */
    @Override
    public List<Owner> findByFirstName(String firstName) {
        List<Owner> owners = ownerRepository.findByFirstName(firstName);
        owners.forEach(owner -> log.info("" + owner));
        return owners;
    }

    /**
     * Busca propietarios por apellido.
     * @param lastName
     * @return lista de propietarios encontrados
     */
    @Override
    public List<Owner> findByLastName(String lastName) {
        List<Owner> owners = ownerRepository.findByLastName(lastName);
        owners.forEach(owner -> log.info("" + owner));
        return owners;
    }

    /**
     * Busca propietarios por ciudad.
     * @param city
     * @return lista de propietarios encontrados
     */
    @Override
    public List<Owner> findByCity(String city) {
        List<Owner> owners = ownerRepository.findByCity(city);
        owners.forEach(owner -> log.info("" + owner));
        return owners;
    }

    /**
     * Obtiene todos los propietarios.
     * @return lista de todos los propietarios
     */
    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}