package com.tecsup.petclinic.util;

import com.tecsup.petclinic.domain.PetTO;
import com.tecsup.petclinic.domain.SpecialtyTO;
import com.tecsup.petclinic.domain.VetTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.entities.Vet;

import java.util.ArrayList;
import java.util.List;

public class TObjectCreator {

	// Métodos para Pet
	public static Pet getPet() {
		return new Pet(1, "Leo", 1, 1, null);
	}

	public static Pet newPet() {
		return new Pet(0, "Punky", 1, 1, null);
	}

	public static Pet newPetCreated() {
		Pet pet = newPet();
		pet.setId(1000);
		return pet;
	}

	public static Pet newPetForUpdate() {
		return new Pet(0, "Bear", 1, 1, null);
	}

	public static Pet newPetCreatedForUpdate() {
		Pet pet = newPetForUpdate();
		pet.setId(4000);
		return pet;
	}

	public static Pet newPetForDelete() {
		return new Pet(0, "Bird", 1, 1, null);
	}

	public static Pet newPetCreatedForDelete() {
		Pet pet = newPetForDelete();
		pet.setId(2000);
		return pet;
	}

	public static List<PetTO> getAllPetTOs() {
		List<PetTO> petTOs = new ArrayList<>();
		petTOs.add(new PetTO(1, "Leo", 1, 1, "2000-09-07"));
		petTOs.add(new PetTO(2, "Basil", 6, 2, "2002-08-06"));
		petTOs.add(new PetTO(3, "Rosy", 2, 3, "2001-04-17"));
		petTOs.add(new PetTO(4, "Jewel", 2, 3, "2000-03-07"));
		petTOs.add(new PetTO(5, "Iggy", 3, 4, "2000-11-30"));
		return petTOs;
	}

	// Métodos para Specialty
	public static Specialty getSpecialty() {
		return new Specialty(1, "Cardiology");
	}

	public static Specialty newSpecialty() {
		return new Specialty(0, "Neurology");
	}

	public static Specialty newSpecialtyCreated() {
		Specialty specialty = newSpecialty();
		specialty.setId(1000);
		return specialty;
	}

	public static Specialty newSpecialtyForUpdate() {
		return new Specialty(0, "Dentistry");
	}

	public static Specialty newSpecialtyCreatedForUpdate() {
		Specialty specialty = newSpecialtyForUpdate();
		specialty.setId(4000);
		return specialty;
	}

	public static Specialty newSpecialtyForDelete() {
		return new Specialty(0, "Surgery");
	}

	public static Specialty newSpecialtyCreatedForDelete() {
		Specialty specialty = newSpecialtyForDelete();
		specialty.setId(2000);
		return specialty;
	}

	public static List<SpecialtyTO> getAllSpecialtyTOs() {
		List<SpecialtyTO> specialtyTOs = new ArrayList<>();
		specialtyTOs.add(new SpecialtyTO(1, "Cardiology"));
		specialtyTOs.add(new SpecialtyTO(2, "Neurology"));
		specialtyTOs.add(new SpecialtyTO(3, "Dentistry"));
		specialtyTOs.add(new SpecialtyTO(4, "Surgery"));
		return specialtyTOs;
	}

	public static SpecialtyTO getSpecialtyTO() {
		return new SpecialtyTO(1, "Cardiology");
	}

	public static SpecialtyTO newSpecialtyTO() {
		return new SpecialtyTO(-1, "Ophthalmology");
	}

	public static SpecialtyTO newSpecialtyTOForDelete() {
		return new SpecialtyTO(10000, "Gastroenterology");
	}

	// Métodos para Vet
	public static Vet getVet() {
		return new Vet(1, "Dr. John", "Doe");
	}

	public static Vet newVet() {
		return new Vet(0, "Dr. Jane", "Smith");
	}

	public static Vet newVetCreated() {
		Vet vet = newVet();
		vet.setId(1000);
		return vet;
	}

	public static Vet newVetForUpdate() {
		return new Vet(0, "Dr. Jim", "Brown");
	}

	public static Vet newVetCreatedForUpdate() {
		Vet vet = newVetForUpdate();
		vet.setId(4000);
		return vet;
	}

	public static Vet newVetForDelete() {
		return new Vet(0, "Dr. Jack", "Green");
	}

	public static Vet newVetCreatedForDelete() {
		Vet vet = newVetForDelete();
		vet.setId(2000);
		return vet;
	}

	public static List<VetTO> getAllVetTOs() {
		List<VetTO> vetTOs = new ArrayList<>();
		vetTOs.add(new VetTO(1, "Dr. John Doe"));
		vetTOs.add(new VetTO(2, "Dr. Jane Smith"));
		vetTOs.add(new VetTO(3, "Dr. Jim Brown"));
		vetTOs.add(new VetTO(4, "Dr. Jack Green"));
		return vetTOs;
	}

	public static VetTO getVetTO() {
		return new VetTO(1, "Dr. John Doe");
	}

	public static VetTO newVetTO() {
		return new VetTO(-1, "Dr. Emily White");
	}

	public static VetTO newVetTOForDelete() {
		return new VetTO(10000, "Dr. Robert Black");
	}
}
