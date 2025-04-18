package com.example.microservice3.repository;

import com.example.microservice3.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface facturerep extends JpaRepository<Facture, Long>   {
}
