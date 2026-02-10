package fr.eni.ludotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JeuDTO {
    private String titre;
    private String reference;
    private Integer ageMin;
    private String description;
    private Integer duree;
    private Float tarifJour;
    private Integer nbExemplairesDisponibles;
}
