package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.domain.SpecialtyTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import com.tecsup.petclinic.mapper.SpecialtyMapper;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import com.tecsup.petclinic.services.SpecialtyService;
import com.tecsup.petclinic.util.TObjectCreator;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class SpecialtyControllerMockitoTest {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SpecialtyRepository specialtyRepository;

	@MockBean
	private SpecialtyService specialtyService;

	SpecialtyMapper mapper = Mappers.getMapper(SpecialtyMapper.class);

	@BeforeEach
	void setUp() {
		// Initialize RestAssured
		RestAssuredMockMvc.mockMvc(mockMvc);
	}

	@AfterEach
	void tearDown() {}

	@Test
	public void testFindAllSpecialties() throws Exception {
		int NRO_RECORD = 3;  // Ajusta el número según tus datos

		List<SpecialtyTO> specialtyTOs = TObjectCreator.getAllSpecialtyTOs();
		List<Specialty> specialties = this.mapper.toSpecialtyList(specialtyTOs);

		Mockito.when(specialtyService.findAll())
				.thenReturn(specialties);

		this.mockMvc.perform(get("/specialties"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.size()", is(NRO_RECORD)));
	}

	@Test
	public void testFindSpecialtyOK() throws Exception {
		SpecialtyTO specialtyTO = TObjectCreator.getSpecialtyTO();
		Specialty specialty = this.mapper.toSpecialty(specialtyTO);

		Mockito.when(specialtyService.findById(specialty.getId()))
				.thenReturn(specialty);

		mockMvc.perform(get("/specialties/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(specialtyTO.getId())))
				.andExpect(jsonPath("$.name", is(specialtyTO.getName())));
	}

	@Test
	public void testFindSpecialtyKO() throws Exception {
		Integer ID_NOT_EXIST = 666;

		Mockito.when(specialtyService.findById(ID_NOT_EXIST))
				.thenThrow(new SpecialtyNotFoundException("Record not found!"));

		mockMvc.perform(get("/specialties/666"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testCreateSpecialty() throws Exception {
		SpecialtyTO newSpecialtyTO = TObjectCreator.newSpecialtyTO();
		Specialty newSpecialty = this.mapper.toSpecialty(newSpecialtyTO);

		Mockito.when(specialtyService.create(newSpecialty))
				.thenReturn(newSpecialty);

		mockMvc.perform(post("/specialties")
						.content(om.writeValueAsString(newSpecialtyTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is(newSpecialtyTO.getName())));
	}

	@Test
	public void testDeleteSpecialty() throws Exception {
		SpecialtyTO newSpecialtyTO = TObjectCreator.newSpecialtyTOForDelete();
		Specialty newSpecialty = this.mapper.toSpecialty(newSpecialtyTO);

		Mockito.when(specialtyService.create(newSpecialty))
				.thenReturn(newSpecialty);

		mockMvc.perform(post("/specialties")
						.content(om.writeValueAsString(newSpecialtyTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		Mockito.doNothing().when(specialtyService).delete(newSpecialty.getId());

		mockMvc.perform(delete("/specialties/" + newSpecialty.getId()))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateSpecialty() throws Exception {
		SpecialtyTO newSpecialtyTO = TObjectCreator.newSpecialtyTO();
		Specialty updatedSpecialty = this.mapper.toSpecialty(newSpecialtyTO);

		Mockito.when(specialtyService.update(updatedSpecialty))
				.thenReturn(updatedSpecialty);

		mockMvc.perform(put("/specialties/" + updatedSpecialty.getId())
						.content(om.writeValueAsString(newSpecialtyTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
