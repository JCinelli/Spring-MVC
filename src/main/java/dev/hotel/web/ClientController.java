package dev.hotel.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.service.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {

	ClientService clientService;

	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	// GET /clients?start=X&size=Y
	@GetMapping
	public List<Client> allClients(@RequestParam Integer start, @RequestParam Integer size) {

		return clientService.listAllClients(start, size);

	}

	// GET /clients/{uuid}
	@GetMapping("{uuid}")
	public ResponseEntity<?> getClient(@PathVariable UUID uuid) {

		Optional<Client> optClient = clientService.recupererClient(uuid);

		if (optClient.isPresent()) {

			return ResponseEntity.status(HttpStatus.OK).body(optClient);

		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identifiant client introuvable . . .");

		}

	}

	@PostMapping
	public ResponseEntity<?> creerClient(@RequestBody @Valid CreerClientRequestDto client,
			BindingResult resultatValidation) {

		if (resultatValidation.hasErrors()) {
			return ResponseEntity.badRequest().body("Erreur");
		}

		return ResponseEntity
				.ok(new CreerClientResponseDto(clientService.newClient(client.getNom(), client.getPrenoms())));
	}

}
