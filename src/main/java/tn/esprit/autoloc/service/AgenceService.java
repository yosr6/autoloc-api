package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AgenceService {

    private final AgenceRepository agenceRepository;

    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    public Optional<Agence> findById(Long id) {
        return agenceRepository.findById(id);
    }

    public Agence save(Agence agence) {
        return agenceRepository.save(agence);
    }

    public Optional<Agence> update(Long id, Agence agenceDetails) {
        return agenceRepository.findById(id).map(existing -> {
            existing.setNom(agenceDetails.getNom());
            existing.setAdresse(agenceDetails.getAdresse());
            existing.setTelephone(agenceDetails.getTelephone());
            return agenceRepository.save(existing);
        });
    }

    public boolean deleteById(Long id) {
        if (agenceRepository.existsById(id)) {
            agenceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
