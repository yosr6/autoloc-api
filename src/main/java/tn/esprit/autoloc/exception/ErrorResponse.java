package tn.esprit.autoloc.exception;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente la structure JSON renvoyée lorsqu'une exception est interceptée par le {@link GlobalExceptionHandler}.
 * Elle contient les informations utiles pour le client (timestamp, code HTTP, message, etc.).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    /** Date‑heure de l'erreur */
    private LocalDateTime timestamp;
    /** Code HTTP (ex. 400, 404, 500) */
    private int status;
    /** Court libellé de l'erreur */
    private String error;
    /** Message détaillé (ou liste des champs invalides) */
    private String message;
    /** Chemin de la requête qui a échoué */
    private String path;
    /** Le cas échéant, liste des messages d'erreurs de validation */
    private List<String> errors;
}
