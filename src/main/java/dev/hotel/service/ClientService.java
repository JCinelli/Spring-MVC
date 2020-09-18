package dev.hotel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@Service
public class ClientService {

	private ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	public List<Client> listAllClients() {

		return clientRepository.findAll();

	}

	public List<Client> listAllClientsPage(Integer start, Integer size) {

		return clientRepository.findAll(PageRequest.of(start, size)).getContent();

	}

	public Optional<Client> recupererClient(UUID uuid) {

		return clientRepository.findById(uuid);

	}

	public List<Optional<Client>> recupererClientByName(String nom) {

		return clientRepository.findByNom(nom);

	}

	@Transactional
	public Client newClient(String nom, String prenoms) {

		Client newClient = new Client(nom, prenoms);

		return clientRepository.save(newClient);

	}

}
