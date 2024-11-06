package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.domain.SpecialtyTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class SpecialtyControllerTest {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testFindAllSpecialties() throws Exception {
		int NRO_RECORD = 3;  // Ajusta el número según tus datos
		int ID_FIRST_RECORD = 1;

		this.mockMvc.perform(get("/specialties"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.size()", is(NRO_RECORD)))
				.andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
	}

	@Test
	public void testFindSpecialtyOK() throws Exception {
		String SPECIALTY_NAME = "Surgery";

		mockMvc.perform(get("/specialties/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is(SPECIALTY_NAME)));
	}

	@Test
	public void testFindSpecialtyKO() throws Exception {
		mockMvc.perform(get("/specialties/666"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testCreateSpecialty() throws Exception {
		String SPECIALTY_NAME = "Dentistry";

		SpecialtyTO newSpecialtyTO = new SpecialtyTO();
		newSpecialtyTO.setName(SPECIALTY_NAME);

		mockMvc.perform(post("/specialties")
						.content(om.writeValueAsString(newSpecialtyTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is(SPECIALTY_NAME)));
	}

	@Test
	public void testDeleteSpecialty() throws Exception {
		String SPECIALTY_NAME = "Emergency";
		SpecialtyTO newSpecialtyTO = new SpecialtyTO();
		newSpecialtyTO.setName(SPECIALTY_NAME);

		ResultActions mvcActions = mockMvc.perform(post("/specialties")
						.content(om.writeValueAsString(newSpecialtyTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		String response = mvcActions.andReturn().getResponse().getContentAsString();
		Integer id = Integer.parseInt(response);

		mockMvc.perform(delete("/specialties/" + id))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteSpecialtyKO() throws Exception {
		mockMvc.perform(delete("/specialties/1000"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateSpecialty() throws Exception {
		String SPECIALTY_NAME = "Dermatology";
		String UPDATED_NAME = "Pediatrics";

		SpecialtyTO newSpecialtyTO = new SpecialtyTO();
		newSpecialtyTO.setName(SPECIALTY_NAME);

		ResultActions mvcActions = mockMvc.perform(post("/specialties")
						.content(om.writeValueAsString(newSpecialtyTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		String response = mvcActions.andReturn().getResponse().getContentAsString();
		Integer id = Integer.parseInt(response);

		SpecialtyTO updatedSpecialtyTO = new SpecialtyTO();
		updatedSpecialtyTO.setId(id);
		updatedSpecialtyTO.setName(UPDATED_NAME);

		mockMvc.perform(put("/specialties/" + id)
						.content(om.writeValueAsString(updatedSpecialtyTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mockMvc.perform(get("/specialties/" + id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(UPDATED_NAME)));
	}
}
