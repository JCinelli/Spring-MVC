package dev.hotel.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import dev.hotel.entite.Client;
import dev.hotel.service.ClientService;
import dev.hotel.web.client.ClientController;

@WebMvcTest(controllers = ClientController.class)
class ClientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientService clientService;

	@Test
	void testFindAllPageable() throws Exception {

		Client client = new Client("Serpent", "Jhon");

		Client client2 = new Client("Sardine", "Michel");

		Mockito.when(clientService.listAllClients(0, 2)).thenReturn(Arrays.asList(client, client2));

		mockMvc.perform(MockMvcRequestBuilders.get("/clients?start=0&size=2")).andExpect(status().isOk())
				.andExpect(jsonPath("[0].nom", is("Serpent"))).andExpect(jsonPath("[0].prenoms", is("Jhon")))
				.andExpect(jsonPath("[1].nom", is("Sardine"))).andExpect(jsonPath("[1].prenoms", is("Michel")));

	}

	@Test
	void testFindByUuid() throws Exception {

		Client client = new Client("Sardine", "Michel");
		UUID id = UUID.randomUUID();
		client.setUuid(id);

		Mockito.when(clientService.recupererClient(id)).thenReturn(Optional.of(client));

		mockMvc.perform(MockMvcRequestBuilders.get("/clients/{uuid}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.nom").value("Sardine")).andExpect(jsonPath("$.prenoms").value("Michel"));

	}

	@Test
	void testPostClient() throws Exception {

		Client client = new Client("Serpent", "Jhon");

		Mockito.when(clientService.newClient("Serpent", "Jhon")).thenReturn(client);

		mockMvc.perform(MockMvcRequestBuilders.post("/clients").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nom\": \"Serpent\", \"prenoms\": \"Jhon\"}")).andExpect(status().isOk());

	}

}
