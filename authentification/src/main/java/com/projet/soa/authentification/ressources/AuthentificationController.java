package com.projet.soa.authentification.ressources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projet.soa.authentification.models.Fournisseur;
import com.projet.soa.authentification.models.Login;
import com.projet.soa.authentification.repositories.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class AuthentificationController {

    @Autowired
    private FournisseurRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private FournisseurProxy fournisseurProxy;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @PostMapping("/register")
    public String saveFournisseur(@RequestBody Fournisseur fournisseur){
        //final String uri = "http://FOURNISSEUR-SERVICE/addFournisseur";
        String result = fournisseurProxy.saveFournisseur(fournisseur);

        return result;
        //return "Added with userName " + fournisseur.getUsername() + " And ID : " + fournisseur.getId();
    }

    @PostMapping("/login")
    public String login(@RequestBody Login login) throws JsonProcessingException {
//        String host1 = discoveryClient.getInstances("fournisseur-service").get(0).getHost();
//        Integer port1 = discoveryClient.getInstances("fournisseur-service").get(0).getPort();
        return fournisseurProxy.login(login);
    }

//    @GetMapping("/findAllFournisseurs")
//    public List<Fournisseur> getFournisseurs(){
//        return repository.findAll();
//    }
//
//    @GetMapping("/findFournisseur/{id}")
//    public Optional<Fournisseur> getFournisseur(@PathVariable int id){
//        return repository.findById(id);
//    }
//
//    @GetMapping("/deleteFournisseur/{id}")
//    public String deleteFournisseur(@PathVariable int id){
//        repository.deleteById(id);
//        return "Fournisseur deleted : " + id;
//    }
}