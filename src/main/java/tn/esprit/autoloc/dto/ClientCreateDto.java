package tn.esprit.autoloc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO utilisé pour la création / mise à jour d'un client. Contient les mêmes champs que {@link ClientDto}
 * sans l'identifiant (qui est généré côté serveur).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateDto {
    @NotBlank(message = "Le nom ne doit pas être vide")
    private String nom;

    @NotBlank(message = "Le prénom ne doit pas être vide")
    private String prenom;

    @Email(message = "L'email doit être valide")
    private String email;

    @Size(min = 8, max = 15, message = "Le téléphone doit contenir entre 8 et 15 caractères")
    private String telephone;

    @NotBlank(message = "Le numéro de permis ne doit pas être vide")
    private String numeroPermis;
}
