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
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.AgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
@Tag(name = "Agence", description = "API de gestion des agences de location — opérations CRUD complètes")
public class AgenceController {

    private final AgenceService agenceService;

    // ─────────────────────────────────────────
    // GET ALL
    // ─────────────────────────────────────────
    @GetMapping
    @Operation(
        summary = "Lister toutes les agences",
        description = "Retourne la liste complète de toutes les agences enregistrées dans le système."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agence.class)))
    })
    public ResponseEntity<List<Agence>> getAll() {
        return ResponseEntity.ok(agenceService.findAll());
    }

    // ─────────────────────────────────────────
    // GET BY ID
    // ─────────────────────────────────────────
    @GetMapping("/{id}")
    @Operation(
        summary = "Trouver une agence par ID",
        description = "Retourne une agence unique identifiée par son ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Agence trouvée",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agence.class))),
        @ApiResponse(responseCode = "404", description = "Agence introuvable", content = @Content)
    })
    public ResponseEntity<Agence> getById(
        @Parameter(description = "ID de l'agence", required = true) @PathVariable Long id
    ) {
        return agenceService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // ─────────────────────────────────────────
    // POST
    // ─────────────────────────────────────────
    @PostMapping
    @Operation(
        summary = "Créer une nouvelle agence",
        description = "Ajoute une nouvelle agence dans le système et retourne la ressource créée."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Agence créée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agence.class))),
        @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content)
    })
    public ResponseEntity<Agence> create(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objet Agence à créer", required = true,
            content = @Content(schema = @Schema(implementation = Agence.class))
        )
        @RequestBody Agence agence
    ) {
        Agence saved = agenceService.save(agence);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ─────────────────────────────────────────
    // PUT
    // ─────────────────────────────────────────
    @PutMapping("/{id}")
    @Operation(
        summary = "Mettre à jour une agence",
        description = "Met à jour les informations d'une agence existante identifiée par son ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Agence mise à jour avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agence.class))),
        @ApiResponse(responseCode = "404", description = "Agence introuvable", content = @Content)
    })
    public ResponseEntity<Agence> update(
        @Parameter(description = "ID de l'agence à mettre à jour", required = true) @PathVariable Long id,
        @RequestBody Agence agence
    ) {
        return agenceService.update(id, agence)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // ─────────────────────────────────────────
    // DELETE
    // ─────────────────────────────────────────
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Supprimer une agence",
        description = "Supprime définitivement une agence identifiée par son ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Agence supprimée avec succès", content = @Content),
        @ApiResponse(responseCode = "404", description = "Agence introuvable", content = @Content)
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID de l'agence à supprimer", required = true) @PathVariable Long id
    ) {
        return agenceService.deleteById(id)
            ? ResponseEntity.noContent().<Void>build()
            : ResponseEntity.notFound().build();
    }
}
