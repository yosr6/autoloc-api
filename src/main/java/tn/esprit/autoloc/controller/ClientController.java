package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.ClientDto;
import tn.esprit.autoloc.dto.ClientCreateDto;
import jakarta.validation.Valid;
import tn.esprit.autoloc.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Client", description = "API de gestion des clients — opérations CRUD complètes")
public class ClientController {

    private final ClientService clientService;
    private final tn.esprit.autoloc.mapper.ClientMapper clientMapper;


    @GetMapping
    @Operation(
        summary = "Lister tous les clients",
        description = "Retourne la liste complète de tous les clients enregistrés dans le système."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    })
    public ResponseEntity<List<tn.esprit.autoloc.dto.ClientDto>> getAll() {
        List<tn.esprit.autoloc.domain.Client> entities = clientService.findAll();
        List<tn.esprit.autoloc.dto.ClientDto> dtos = entities.stream()
                .map(clientMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/{id}")
    @Operation(
        summary = "Trouver un client par ID",
        description = "Retourne un client unique identifié par son ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Client trouvé",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
        @ApiResponse(responseCode = "404", description = "Client introuvable", content = @Content)
    })
    public ResponseEntity<tn.esprit.autoloc.dto.ClientDto> getById(
        @Parameter(description = "ID du client", required = true) @PathVariable Long id
    ) {
        return clientService.findById(id)
            .map(clientMapper::toDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(
        summary = "Créer un nouveau client",
        description = "Ajoute un nouveau client dans le système et retourne la ressource créée."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Client créé avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
        @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content)
    })
    public ResponseEntity<tn.esprit.autoloc.dto.ClientDto> create(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objet Client à créer", required = true,
            content = @Content(schema = @Schema(implementation = tn.esprit.autoloc.dto.ClientCreateDto.class))
        )
        @Valid @RequestBody tn.esprit.autoloc.dto.ClientCreateDto clientDto
    ) {
        tn.esprit.autoloc.domain.Client client = clientMapper.fromCreateDto(clientDto);
        tn.esprit.autoloc.domain.Client saved = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientMapper.toDto(saved));
    }

    // ─────────────────────────────────────────
    // PUT
    // ─────────────────────────────────────────
    @PutMapping("/{id}")
    @Operation(
        summary = "Mettre à jour un client",
        description = "Met à jour les informations d'un client existant identifié par son ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Client mis à jour avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
        @ApiResponse(responseCode = "404", description = "Client introuvable", content = @Content)
    })
    public ResponseEntity<tn.esprit.autoloc.dto.ClientDto> update(
        @Parameter(description = "ID du client à mettre à jour", required = true) @PathVariable Long id,
        @Valid @RequestBody tn.esprit.autoloc.dto.ClientCreateDto clientDto
    ) {
        tn.esprit.autoloc.domain.Client client = clientMapper.fromCreateDto(clientDto);
        return clientService.update(id, client)
            .map(clientMapper::toDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // ─────────────────────────────────────────
    // DELETE
    // ─────────────────────────────────────────
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Supprimer un client",
        description = "Supprime définitivement un client identifié par son ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Client supprimé avec succès", content = @Content),
        @ApiResponse(responseCode = "404", description = "Client introuvable", content = @Content)
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID du client à supprimer", required = true) @PathVariable Long id
    ) {
        return clientService.deleteById(id)
            ? ResponseEntity.noContent().<Void>build()
            : ResponseEntity.notFound().build();
    }
}
