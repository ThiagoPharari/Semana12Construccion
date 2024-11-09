package com.tecsup.petclinic.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jgomezm
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VetTO {

    private Integer id;

    private String firstName;

    private String lastName;

}
