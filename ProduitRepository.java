package com.example.microservice3.repository;

import com.example.microservice3.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByNomContainingIgnoreCase(String nom);
    List<Produit> findByPrixBetween(double prixMin, double prixMax);
}
