package tn.esprit.autoloc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> status = new HashMap<>();
        status.put("application", "autoloc-api");
        status.put("status", "UP & RUNNING");
        status.put("message", "Bienvenue sur l'API AutoLoc - Gestion de location automobile");
        status.put("swagger_documentation", "/swagger-ui/index.html");
        status.put("tables_generees", 10);
        return status;
    }
}
