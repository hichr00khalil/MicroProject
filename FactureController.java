package com.example.microservice3.controller;


import com.example.microservice3.entity.Facture;
import com.example.microservice3.entity.StatutPay;
import com.example.microservice3.service.factureImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    private final factureImp factureService;

    public FactureController(factureImp factureService) {
        this.factureService = factureService;
    }

    @PostMapping
    public ResponseEntity<Facture> createFacture(@RequestBody Facture facture) {
        Facture createdFacture = factureService.createFacture(facture);
        return new ResponseEntity<>(createdFacture, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable Long id) {
        Facture facture = factureService.getFactureById(id);
        return ResponseEntity.ok(facture);
    }

    @GetMapping
    public ResponseEntity<List<Facture>> getAllFactures() {
        List<Facture> factures = factureService.getAllFactures();
        return ResponseEntity.ok(factures);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facture> updateFacture(@PathVariable Long id, @RequestBody Facture facture) {
        Facture updatedFacture = factureService.updateFacture(id, facture);
        return ResponseEntity.ok(updatedFacture);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        factureService.deleteFacture(id);
        return ResponseEntity.noContent().build();
    }


}
