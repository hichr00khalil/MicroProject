package com.example.microservice3.repository;

import com.example.microservice3.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface commandeRep extends JpaRepository<Commande, Long> {
}
