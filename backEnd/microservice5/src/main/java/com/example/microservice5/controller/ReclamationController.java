package com.example.microservice5.controller;


import com.example.microservice5.entity.Reclamation;
import com.example.microservice5.service.ReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("mic5/")
public class ReclamationController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello I'm Microservice 5 : Restaurant/reclamation ";
    }

    @Autowired
    private ReclamationService reclamationService;


    @PostMapping("/createReclamation")
    public Reclamation createReclamation(@RequestBody Reclamation reclamation) {
        return reclamationService.saveReclamation(reclamation);
    }

    @GetMapping("/getAllReclamations")
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/getReclamation/{id}")
    public Reclamation getReclamation(@PathVariable Long id) {
        return reclamationService.getReclamationById(id);
    }

    @DeleteMapping("/deleteReclamation/{id}")
    public void deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
    }


}

