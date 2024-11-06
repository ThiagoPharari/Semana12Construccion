package com.tecsup.petclinic.exception;

/**
 * Excepción lanzada cuando no se encuentra un Vet
 *
 */
public class VetNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public VetNotFoundException(String message) {
        super(message);
    }
}
