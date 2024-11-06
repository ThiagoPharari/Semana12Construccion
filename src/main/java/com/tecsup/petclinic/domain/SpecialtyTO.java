package com.tecsup.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para Specialty
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpecialtyTO {

    private Integer id;

    private String name;

}
