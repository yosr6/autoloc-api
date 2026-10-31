package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    public Optional<Vehicule> findById(Long id) {
        return vehiculeRepository.findById(id);
    }

    public Vehicule save(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public Optional<Vehicule> update(Long id, Vehicule vehiculeDetails) {
        return vehiculeRepository.findById(id).map(existing -> {
            existing.setImmatriculation(vehiculeDetails.getImmatriculation());
            existing.setMarque(vehiculeDetails.getMarque());
            existing.setModele(vehiculeDetails.getModele());
            existing.setAnnee(vehiculeDetails.getAnnee());
            existing.setPrixJournalier(vehiculeDetails.getPrixJournalier());
            existing.setStatut(vehiculeDetails.getStatut());
            existing.setCategorie(vehiculeDetails.getCategorie());
            return vehiculeRepository.save(existing);
        });
    }

    public boolean deleteById(Long id) {
        if (vehiculeRepository.existsById(id)) {
            vehiculeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
