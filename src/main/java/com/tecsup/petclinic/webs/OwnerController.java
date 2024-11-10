package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.mapper.OwnerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;

import java.util.List;

/**
 *
 * @author jgomezm
 *
 */
@RestController
@Slf4j
public class OwnerController {

    private OwnerService ownerService;
    private OwnerMapper mapper;

    /**
     * Constructor
     *
     * @param ownerService
     * @param mapper
     */
    public OwnerController(OwnerService ownerService, OwnerMapper mapper) {
        this.ownerService = ownerService;
        this.mapper = mapper;
    }

    /**
     * Get all owners
     *
     * @return
     */
    @GetMapping(value = "/owners")
    public ResponseEntity<List<OwnerTO>> findAllOwners() {

        List<Owner> owners = ownerService.findAll();
        log.info("owners: " + owners);

        List<OwnerTO> ownersTO = this.mapper.toOwnerTOList(owners);
        log.info("ownersTO: " + ownersTO);

        return ResponseEntity.ok(ownersTO);
    }

    /**
     * Create owner
     *
     * @param ownerTO
     * @return
     */
    @PostMapping(value = "/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerTO> create(@RequestBody OwnerTO ownerTO) {

        Owner newOwner = this.mapper.toOwner(ownerTO);
        OwnerTO newOwnerTO = this.mapper.toOwnerTO(ownerService.create(newOwner));

        return ResponseEntity.status(HttpStatus.CREATED).body(newOwnerTO);
    }

    /**
     * Find owner by id
     *
     * @param id
     * @return
     * @throws OwnerNotFoundException
     */
    @GetMapping(value = "/owners/{id}")
    public ResponseEntity<OwnerTO> findById(@PathVariable Integer id) {

        OwnerTO ownerTO;

        try {
            Owner owner = ownerService.findById(id);
            ownerTO = this.mapper.toOwnerTO(owner);

        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ownerTO);
    }

    /**
     * Update owner
     *
     * @param ownerTO
     * @param id
     * @return
     */
    @PutMapping(value = "/owners/{id}")
    public ResponseEntity<OwnerTO> update(@RequestBody OwnerTO ownerTO, @PathVariable Integer id) {

        OwnerTO updateOwnerTO;

        try {
            Owner updateOwner = ownerService.findById(id);

            updateOwner.setFirstName(ownerTO.getFirstName());
            updateOwner.setLastName(ownerTO.getLastName());
            updateOwner.setAddress(ownerTO.getAddress());
            updateOwner.setCity(ownerTO.getCity());
            updateOwner.setTelephone(ownerTO.getTelephone());

            ownerService.update(updateOwner);

            updateOwnerTO = this.mapper.toOwnerTO(updateOwner);

        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updateOwnerTO);
    }

    /**
     * Delete owner by id
     *
     * @param id
     */
    @DeleteMapping(value = "/owners/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        try {
            ownerService.delete(id);
            return ResponseEntity.ok("Deleted ID: " + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}