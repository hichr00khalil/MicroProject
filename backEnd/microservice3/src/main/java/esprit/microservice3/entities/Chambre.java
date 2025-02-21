package esprit.microservice3.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chambre {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private int id;
    private Long numeroChambre;
    @Enumerated(EnumType.STRING)
    private TypeChambre typeC ;


