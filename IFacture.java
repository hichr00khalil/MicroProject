package com.example.microservice3.service;

import com.example.microservice3.entity.Facture;

import java.util.List;

public interface IFacture {
    Facture createFacture(Facture facture);
    Facture getFactureById(Long id);
    List<Facture> getAllFactures();
    Facture updateFacture(Long id, Facture facture);
    void deleteFacture(Long id);
}
