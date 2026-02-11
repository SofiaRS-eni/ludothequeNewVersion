package fr.eni.ludotheque.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.dto.JeuDTO;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import fr.eni.ludotheque.exceptions.DataNotFound;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JeuServiceImpl implements JeuService{
	@NonNull
	private JeuRepository jeuRepository;
	
	@NonNull
	private ExemplaireRepository exemplaireRepository;


	@Override
	public Jeu ajouterUnJeu(JeuDTO jeuDTO)
	{
		Jeu jeu = new Jeu();
		BeanUtils.copyProperties(jeuDTO, jeu);
		Jeu newJeu = null;
		newJeu = jeuRepository.save(jeu);
		return newJeu;
	}

	/*
	@Override
	public Jeu ajouterJeu(Jeu jeu) {
		
		jeuRepository.save(jeu);


		return jeu;
	}*/


	@Override
	public Jeu trouverJeuParNoJeu(Integer noJeu) {
		Optional<Jeu> optJeu = jeuRepository.findById(noJeu);
		
		if(optJeu.isEmpty()) {
			throw new DataNotFound("Jeu", noJeu);
		}
		return optJeu.get();
		
	}

/*
	@Override
	public List<Jeu> listeJeuxCatalogue(String filtreTitre) {
		List<Jeu> jeux = jeuRepository.findAllJeuxAvecNbExemplaires(filtreTitre);
		
		for(Jeu jeu : jeux) {
			int nbExemplairesDisponibles = exemplaireRepository.nbExemplairesDisponibleByNoJeu(jeu.getNoJeu());
			jeu.setNbExemplairesDisponibles(nbExemplairesDisponibles);
		}
		
		return jeux;
	}*/

	@Override
	public List<Jeu> getAllJeu()
	{
		List<Jeu> jeu = jeuRepository.findAll();
		return jeu;
	}

}
