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
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.VehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
@Tag(name = "Vehicule", description = "API de gestion des véhicules — opérations CRUD complètes")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    // ─────────────────────────────────────────
    // GET ALL
    // ─────────────────────────────────────────
    @GetMapping
    @Operation(
        summary = "Lister tous les véhicules",
        description = "Retourne la liste complète de tous les véhicules enregistrés dans le système."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicule.class)))
    })
    public ResponseEntity<List<Vehicule>> getAll() {
        return ResponseEntity.ok(vehiculeService.findAll());
    }

    // ─────────────────────────────────────────
    // GET BY ID
    // ─────────────────────────────────────────
    @GetMapping("/{id}")
    @Operation(
        summary = "Trouver un véhicule par ID",
        description = "Retourne un véhicule unique identifié par son ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Véhicule trouvé",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicule.class))),
        @ApiResponse(responseCode = "404", description = "Véhicule introuvable", content = @Content)
    })
    public ResponseEntity<Vehicule> getById(
        @Parameter(description = "ID du véhicule", required = true) @PathVariable Long id
    ) {
        return vehiculeService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // ─────────────────────────────────────────
    // POST
    // ─────────────────────────────────────────
    @PostMapping
    @Operation(
        summary = "Créer un nouveau véhicule",
        description = "Ajoute un nouveau véhicule dans le système et retourne la ressource créée."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Véhicule créé avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicule.class))),
        @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content)
    })
    public ResponseEntity<Vehicule> create(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objet Véhicule à créer", required = true,
            content = @Content(schema = @Schema(implementation = Vehicule.class))
        )
        @RequestBody Vehicule vehicule
    ) {
        Vehicule saved = vehiculeService.save(vehicule);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ─────────────────────────────────────────
    // PUT
    // ─────────────────────────────────────────
    @PutMapping("/{id}")
    @Operation(
        summary = "Mettre à jour un véhicule",
        description = "Met à jour les informations d'un véhicule existant identifié par son ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Véhicule mis à jour avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicule.class))),
        @ApiResponse(responseCode = "404", description = "Véhicule introuvable", content = @Content)
    })
    public ResponseEntity<Vehicule> update(
        @Parameter(description = "ID du véhicule à mettre à jour", required = true) @PathVariable Long id,
        @RequestBody Vehicule vehicule
    ) {
        return vehiculeService.update(id, vehicule)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // ─────────────────────────────────────────
    // DELETE
    // ─────────────────────────────────────────
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Supprimer un véhicule",
        description = "Supprime définitivement un véhicule identifié par son ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Véhicule supprimé avec succès", content = @Content),
        @ApiResponse(responseCode = "404", description = "Véhicule introuvable", content = @Content)
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID du véhicule à supprimer", required = true) @PathVariable Long id
    ) {
        return vehiculeService.deleteById(id)
            ? ResponseEntity.noContent().<Void>build()
            : ResponseEntity.notFound().build();
    }
}
