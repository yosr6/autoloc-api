package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> update(Long id, Client clientDetails) {
        return clientRepository.findById(id).map(existing -> {
            existing.setNom(clientDetails.getNom());
            existing.setPrenom(clientDetails.getPrenom());
            existing.setEmail(clientDetails.getEmail());
            existing.setTelephone(clientDetails.getTelephone());
            existing.setNumeroPermis(clientDetails.getNumeroPermis());
            return clientRepository.save(existing);
        });
    }

    public boolean deleteById(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
