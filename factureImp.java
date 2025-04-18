package com.example.microservice3.service;

import com.example.microservice3.entity.Facture;
import com.example.microservice3.repository.facturerep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class factureImp implements IFacture {

    private final facturerep facturerep;

    public factureImp(facturerep factureRepository) {
        this.facturerep = factureRepository;
    }

    @Override
    public Facture createFacture(Facture facture) {
        return facturerep.save(facture);
    }

    @Override
    public Facture getFactureById(Long id) {
        return facturerep.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture not found with id: " + id));
    }

    @Override
    public List<Facture> getAllFactures() {
        return facturerep.findAll();
    }

    @Override
    public Facture updateFacture(Long id, Facture facture) {
        Facture existingFacture = getFactureById(id);
        existingFacture.setNbre_total(facture.getNbre_total());
        existingFacture.setStatutPay(facture.getStatutPay());
        existingFacture.setCommande(facture.getCommande());
        return facturerep.save(existingFacture);
    }

    @Override
    public void deleteFacture(Long id) {
        Facture facture = getFactureById(id);
        facturerep.delete(facture);
    }

}
