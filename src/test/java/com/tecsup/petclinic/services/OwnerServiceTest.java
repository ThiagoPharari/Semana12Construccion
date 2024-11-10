package com.tecsup.petclinic.services;



import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.fail;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;


import java.util.List;


@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;


    @Test
    public void testFindOwnerById() {

        String NAME_EXPECTED = "George";

        Integer ID = 1;

        Owner owner = null;

        try {
            owner = this.ownerService.findById(ID);
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }
        assertEquals(NAME_EXPECTED, owner.getName());
    }

    @Test
    public void testFindOwnerByFirstName() {

        String FIND_FIRST_NAME = "George";
        int SIZE_EXPECTED = 1;

        List<Owner> owners = this.ownerService.findByFirstName(FIND_FIRST_NAME);

        assertEquals(SIZE_EXPECTED, owners.size());
    }

    @Test
    public void testFindOwnerByLastName() {

        String FIND_LAST_NAME = "Franklin";
        int SIZE_EXPECTED = 1;

        List<Owner> owners = this.ownerService.findByLastName(FIND_LAST_NAME);

        assertEquals(SIZE_EXPECTED, owners.size());
    }

    @Test
    public void testFindOwnerByCity() {

        String CITY = "McFarland";
        int SIZE_EXPECTED = 1;

        List<Owner> owners = this.ownerService.findByCity(CITY);

        assertEquals(SIZE_EXPECTED, owners.size());
    }
}