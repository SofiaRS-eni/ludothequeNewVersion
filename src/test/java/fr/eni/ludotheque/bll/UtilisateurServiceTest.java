package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Utilisateur;
import fr.eni.ludotheque.dal.UtilisateurRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
public class UtilisateurServiceTest {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @DisplayName("Ajout utilisateur")
    public void testAjoutUser()
    {
        //Arrange
        Utilisateur user1 = new Utilisateur("employe","employe","EMPLOYE");
        utilisateurRepository.save(user1);
    }
}
