package com.tecsup.petclinic.exception;

/**
 * Excepción lanzada cuando no se encuentra una Specialty
 *
 */
public class SpecialtyNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public SpecialtyNotFoundException(String message) {
    super(message);
  }
}
