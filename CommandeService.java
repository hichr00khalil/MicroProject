package com.example.microservice3.service;

import com.example.microservice3.entity.Commande;

import java.util.List;

public interface CommandeService {
    Commande createCommande(Commande commande);
    Commande getCommandeById(Long id);
    List<Commande> getAllCommandes();
    Commande updateCommande(Long id, Commande commande);
    void deleteCommande(Long id);
}
