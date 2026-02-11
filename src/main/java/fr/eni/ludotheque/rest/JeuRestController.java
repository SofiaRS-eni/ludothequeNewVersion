package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dto.JeuDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
public class JeuRestController {

    private final JeuService jeuService;

    public JeuRestController(JeuService jeuService) {
        this.jeuService = jeuService;
    }

    //Liste jeu
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Jeu> getAllJeu()
    {
        List Jeu = jeuService.getAllJeu();
        return Jeu;
    }

    //Ajout d'un jeu
    @PostMapping
    public ResponseEntity<Jeu> createJeu(@Valid @RequestBody JeuDTO jeu, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Jeu createjeu = jeuService.ajouterUnJeu(jeu);
        return ResponseEntity.status(HttpStatus.CREATED).body(createjeu);
    }

    //Liste le catalogue du jeu


}
