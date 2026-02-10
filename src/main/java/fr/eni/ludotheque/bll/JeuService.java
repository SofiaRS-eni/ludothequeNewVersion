package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dto.JeuDTO;

import java.util.List;

public interface JeuService {

	Jeu ajouterUnJeu(JeuDTO jeuDTO);

	//Jeu ajouterJeu(Jeu jeu);
	
	Jeu trouverJeuParNoJeu(Integer noJeu);
	
	List<Jeu> listeJeuxCatalogue(String filtreTitre);

	List<Jeu> getAllJeu();
}
