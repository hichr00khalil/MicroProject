package com.example.microservice3.service;

import com.example.microservice3.entity.Commande;
import com.example.microservice3.repository.commandeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private  commandeRep commandeRep;

    @Autowired
    public CommandeServiceImpl(commandeRep commandeRep) {
        this.commandeRep = commandeRep;
    }

    @Override
    public Commande createCommande(Commande commande) {
        return commandeRep.save(commande);
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id " + id));
    }

    @Override
    public List<Commande> getAllCommandes() {
        return List.of();
    }

   /* @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }*/

    @Override
    public Commande updateCommande(Long id, Commande commande) {
        Commande existingCommande = getCommandeById(id);
        existingCommande.setStatut(commande.getStatut());
        existingCommande.setComment(commande.getComment());
        existingCommande.setProduits(commande.getProduits());
        existingCommande.setFacture(commande.getFacture());
        return commandeRep.save(existingCommande);
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRep.deleteById(id);
    }
}
