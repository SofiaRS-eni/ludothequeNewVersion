package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {
    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    //List tous les clients
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Client> getAllClients()
    {
        List clients = clientService.getAllClients();
        return clients;
    }


    //Affiche le client par rapport a son id
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id)
    {
        Optional<Client> clientOpt = Optional.ofNullable(clientService.trouverClientParId(id));
        if(clientOpt.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(clientOpt.get());
    }

    //Ajout d'un nouveau client
    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Client createClient = clientService.ajouterClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createClient);
    }

    //Efface le client par rapport a son id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer id)
    {
        try{
            clientService.deleteClient(id);
            return ResponseEntity.status(HttpStatus.OK).body("reussi");
        }
        catch (DataNotFound dnt)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client non trouvé");
        }
    }

    //Modif client entier
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id,@Valid @RequestBody ClientDTO clientDTO)
    {
        try{
            Client client = clientService.modifierClient(id, clientDTO);
            return ResponseEntity.ok(client);
        }catch (DataNotFound dnt)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    //Modif que l'adresse client
    @PatchMapping("/{id}")
    public ResponseEntity<Client> updateAdresseClient(@PathVariable Integer id, @Valid @RequestBody AdresseDTO adresseDTO)
    {
        try{
            Client clientAdresse = clientService.modifierAdresse(id, adresseDTO);
            return ResponseEntity.ok(clientAdresse);
        }catch (DataNotFound dnt)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
