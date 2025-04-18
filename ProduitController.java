package com.example.microservice3.controller;

import com.example.microservice3.entity.Produit;
import com.example.microservice3.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProduitController {
    @Autowired
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        Produit createdProduit = produitService.createProduit(produit);
        return new ResponseEntity<>(createdProduit, HttpStatus.CREATED);
    }

   /* @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Produit produit = produitService.getProduitById(id);
        return ResponseEntity.ok(produit);
    }*/

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        Produit updatedProduit = produitService.updateProduit(id, produit);
        return ResponseEntity.ok(updatedProduit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints
    @GetMapping("/search")
    public ResponseEntity<List<Produit>> searchProduits(@RequestParam String nom) {
        List<Produit> produits = produitService.searchProduitsByName(nom);
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Produit>> filterProduitsByPrice(
            @RequestParam double min,
            @RequestParam double max) {
        List<Produit> produits = produitService.filterProduitsByPriceRange(min, max);
        return ResponseEntity.ok(produits);
    }
}

