package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.VetTO;
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

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class VetControllerTest {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testFindAllVets() throws Exception {
		int ID_FIRST_RECORD = 1;

		this.mockMvc.perform(get("/vets"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
	}

	@Test
	public void testFindVetOK() throws Exception {
		String VET_FIRST_NAME = "James";

		mockMvc.perform(get("/vets/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.firstName", is(VET_FIRST_NAME)));
	}

	@Test
	public void testFindVetKO() throws Exception {
		mockMvc.perform(get("/vets/666"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testCreateVet() throws Exception {
		String VET_FIRST_NAME = "Thiago";
		String VET_LAST_NAME = "Pharari";

		VetTO newVetTO = new VetTO();
		newVetTO.setFirstName(VET_FIRST_NAME);
		newVetTO.setLastName(VET_LAST_NAME);

		mockMvc.perform(post("/vets")
						.content(om.writeValueAsString(newVetTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", is(VET_FIRST_NAME)))
				.andExpect(jsonPath("$.lastName", is(VET_LAST_NAME)));
	}


	@Test
	public void testDeleteVet() throws Exception {
		Integer vetId = 14;

		mockMvc.perform(delete("/vets/" + vetId))
				.andDo(print())
				.andExpect(status().isOk());

		mockMvc.perform(get("/vets/" + vetId))
				.andExpect(status().isNotFound());
	}
}
