package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.mapper.VetMapper;
import com.tecsup.petclinic.domain.VetTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Vet Controller
 *
 * @Author jgomezm
 */
@RestController
@Slf4j
public class VetController {

    private final VetService vetService;
    private final VetMapper mapper;

    public VetController(VetService vetService, VetMapper mapper) {
        this.vetService = vetService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/vets")
    public ResponseEntity<List<VetTO>> findAllVets() {
        List<Vet> vets = vetService.findAll();
        List<VetTO> vetsTO = mapper.toVetTOList(vets);
        return ResponseEntity.ok(vetsTO);
    }

    @PostMapping(value = "/vets")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VetTO> create(@RequestBody VetTO vetTO) {
        Vet newVet = mapper.toVet(vetTO);
        VetTO newVetTO = mapper.toVetTO(vetService.create(newVet));
        return ResponseEntity.status(HttpStatus.CREATED).body(newVetTO);
    }

    @GetMapping(value = "/vets/{id}")
    public ResponseEntity<VetTO> findById(@PathVariable Integer id) {
        try {
            Vet vet = vetService.findById(id);
            VetTO vetTO = mapper.toVetTO(vet);
            return ResponseEntity.ok(vetTO);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/vets/{id}")
    public ResponseEntity<VetTO> update(@RequestBody VetTO vetTO, @PathVariable Integer id) {
        try {
            Vet updateVet = vetService.findById(id);
            updateVet.setFirstName(vetTO.getFirstName());
            updateVet.setLastName(vetTO.getLastName());
            vetService.update(updateVet);
            VetTO updateVetTO = mapper.toVetTO(updateVet);
            return ResponseEntity.ok(updateVetTO);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/vets/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            vetService.delete(id);
            return ResponseEntity.ok("Deleted Vet ID: " + id);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
