package com.example.microservice3.service;

import com.example.microservice3.entity.Produit;

import java.util.List;
import java.util.Optional;

public interface ProduitService  {
    Produit createProduit(Produit produit);
    Optional<Produit> getProduitById(Long id);
    List<Produit> getAllProduits();
    Produit updateProduit(Long id, Produit produit);
    void deleteProduit(Long id);

    // Additional business methods
    List<Produit> searchProduitsByName(String nom);
    List<Produit> filterProduitsByPriceRange(double minPrice, double maxPrice);
}
