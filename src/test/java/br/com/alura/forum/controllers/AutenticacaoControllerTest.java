package br.com.alura.forum.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AutenticacaoControllerTest {
	
	@Autowired private MockMvc mockMvc; 

	@Test
	void test() throws Exception {
		final String json = "{\"email\": \"moderador@email.com\",\"password\": \"1235456\"}";
		mockMvc.perform(post("/auth")
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
