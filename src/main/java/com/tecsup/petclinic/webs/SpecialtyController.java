package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.mapper.SpecialtyMapper;
import com.tecsup.petclinic.domain.SpecialtyTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import com.tecsup.petclinic.services.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Specialty Controller
 *
 * @Author jgomezm
 */
@RestController
@Slf4j
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final SpecialtyMapper mapper;

    public SpecialtyController(SpecialtyService specialtyService, SpecialtyMapper mapper) {
        this.specialtyService = specialtyService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/specialties")
    public ResponseEntity<List<SpecialtyTO>> findAllSpecialties() {
        List<Specialty> specialties = specialtyService.findAll();
        List<SpecialtyTO> specialtiesTO = mapper.toSpecialtyTOList(specialties);
        return ResponseEntity.ok(specialtiesTO);
    }

    @PostMapping(value = "/specialties")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SpecialtyTO> create(@RequestBody SpecialtyTO specialtyTO) {
        Specialty newSpecialty = mapper.toSpecialty(specialtyTO);
        SpecialtyTO newSpecialtyTO = mapper.toSpecialtyTO(specialtyService.create(newSpecialty));
        return ResponseEntity.status(HttpStatus.CREATED).body(newSpecialtyTO);
    }

    @GetMapping(value = "/specialties/{id}")
    public ResponseEntity<SpecialtyTO> findById(@PathVariable Integer id) {
        try {
            Specialty specialty = specialtyService.findById(id);
            SpecialtyTO specialtyTO = mapper.toSpecialtyTO(specialty);
            return ResponseEntity.ok(specialtyTO);
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/specialties/{id}")
    public ResponseEntity<SpecialtyTO> update(@RequestBody SpecialtyTO specialtyTO, @PathVariable Integer id) {
        try {
            Specialty updateSpecialty = specialtyService.findById(id);
            updateSpecialty.setName(specialtyTO.getName());
            specialtyService.update(updateSpecialty);
            SpecialtyTO updateSpecialtyTO = mapper.toSpecialtyTO(updateSpecialty);
            return ResponseEntity.ok(updateSpecialtyTO);
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/specialties/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            specialtyService.delete(id);
            return ResponseEntity.ok("Deleted Specialty ID: " + id);
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
