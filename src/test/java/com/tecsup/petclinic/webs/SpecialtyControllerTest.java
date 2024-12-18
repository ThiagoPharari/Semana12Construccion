package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * SpecialtyControllerTest
 */
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class SpecialtyControllerTest {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testFindAllSpecialties() throws Exception {
		int ID_FIRST_RECORD = 3;

		this.mockMvc.perform(get("/specialties"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
	}

	@Test
	public void testFindSpecialtyOK() throws Exception {
		String SPECIALTY_NAME = "radiology";

		mockMvc.perform(get("/specialties/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
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
		String SPECIALTY_NAME = "Dermatology";

		SpecialtyTO newSpecialtyTO = new SpecialtyTO();
		newSpecialtyTO.setName(SPECIALTY_NAME);

		mockMvc.perform(post("/specialties")
						.content(om.writeValueAsString(newSpecialtyTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is(SPECIALTY_NAME)));
	}

	@Test
	public void testDeleteSpecialty() throws Exception {
		Integer specialtyId = 9;

		mockMvc.perform(get("/specialties/" + specialtyId))
				.andDo(print())
				.andExpect(status().isOk());

		mockMvc.perform(delete("/specialties/" + specialtyId))
				.andExpect(status().isOk());

		mockMvc.perform(get("/specialties/" + specialtyId))
				.andExpect(status().isNotFound());
	}


	@Test
	public void testDeleteSpecialtyKO() throws Exception {
		mockMvc.perform(delete("/specialties/1000"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateSpecialty() throws Exception {
		String SPECIALTY_NAME = "Cardiology";
		String UPDATED_SPECIALTY_NAME = "Neurology";

		SpecialtyTO newSpecialtyTO = new SpecialtyTO();
		newSpecialtyTO.setName(SPECIALTY_NAME);

		// CREATE
		ResultActions mvcActions = mockMvc.perform(post("/specialties")
						.content(om.writeValueAsString(newSpecialtyTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());

		String response = mvcActions.andReturn().getResponse().getContentAsString();
		Integer id = JsonPath.parse(response).read("$.id");

		// UPDATE
		SpecialtyTO updatedSpecialtyTO = new SpecialtyTO();
		updatedSpecialtyTO.setId(id);
		updatedSpecialtyTO.setName(UPDATED_SPECIALTY_NAME);

		mockMvc.perform(put("/specialties/" + id)
						.content(om.writeValueAsString(updatedSpecialtyTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());

		// FIND
		mockMvc.perform(get("/specialties/" + id))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(id)))
				.andExpect(jsonPath("$.name", is(UPDATED_SPECIALTY_NAME)));

		// DELETE
		mockMvc.perform(delete("/specialties/" + id))
				.andExpect(status().isOk());
	}
}
