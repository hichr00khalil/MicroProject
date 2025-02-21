package esprit.microservice2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int capacite;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foyer")
    private Set<Foyer> blocs;


}