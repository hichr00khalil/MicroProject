package com.example.microservice3.service;

import com.example.microservice3.entity.Produit;
import com.example.microservice3.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit updateProduit(Long id, Produit produit) {
        Optional<Produit> existingProduit = getProduitById(id);
        existingProduit.get().setNom(Produit.getNom());
        existingProduit.get().setPrix(Produit.getPrix());
        existingProduit.get().setDescription(Produit.getDescription());
        return Optional.of(produitRepository.save(existingProduit));
    }

    @Override
    public void deleteProduit(Long id) {
        if (produitRepository.existsById(id)) {
            produitRepository.deleteById(id);
        } //else {
            //throw new ProduitNotFoundException(id);
        //}

    }

    @Override
    public List<Produit> searchProduitsByName(String nom) {
        return produitRepository.findByNomContainingIgnoreCase(nom);
    }

    @Override
    public List<Produit> filterProduitsByPriceRange(double minPrice, double maxPrice) {
        return produitRepository.findByPrixBetween(minPrice, maxPrice);
    }

}

