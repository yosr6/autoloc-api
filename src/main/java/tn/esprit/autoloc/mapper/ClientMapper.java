package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.dto.ClientDto;
import tn.esprit.autoloc.dto.ClientCreateDto;

/**
 * Mapper manuel entre l'entité {@link Client} et les DTO {@link ClientDto} / {@link ClientCreateDto}.
 * Utilisé par les contrôleurs pour éviter d'exposer directement les entités JPA.
 */
@Component
public class ClientMapper {

    public ClientDto toDto(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .numeroPermis(client.getNumeroPermis())
                .build();
    }

    public Client fromCreateDto(ClientCreateDto dto) {
        if (dto == null) {
            return null;
        }
        Client client = new Client();
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setNumeroPermis(dto.getNumeroPermis());
        return client;
    }
}
