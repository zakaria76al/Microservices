package com.projet.soa.authentification.ressources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projet.soa.authentification.models.Fournisseur;
import com.projet.soa.authentification.models.Login;
import com.projet.soa.authentification.repositories.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthentificationController {

    @Autowired
    private FournisseurRepository repository;

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @PostMapping("/register")
    public String saveFournisseur(@RequestBody Fournisseur fournisseur){
        //repository.save(fournisseur);
//        String host1 = discoveryClient.getInstances("fournisseur-service").get(0).getHost();
//        Integer port1 = discoveryClient.getInstances("fournisseur-service").get(0).getPort();
        final String uri = "http://FOURNISSEUR-SERVICE/addFournisseur";
        //RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri, fournisseur, String.class);

        System.out.println(result);
        return "Added with userName " + fournisseur.getUsername() + " And ID : " + fournisseur.getId();
    }

    @PostMapping("/login")
    public String login(@RequestBody Login login) throws JsonProcessingException {
//        String host1 = discoveryClient.getInstances("fournisseur-service").get(0).getHost();
//        Integer port1 = discoveryClient.getInstances("fournisseur-service").get(0).getPort();
        final String uri = "http://FOURNISSEUR-SERVICE/findFournisseurByUser/" + login.getUsername();
        Fournisseur fournisseur = restTemplate.getForObject(uri, Fournisseur.class);// restTemplate.getForObject(uri, List.class);
        if(fournisseur.getId() == 0)
        {
            return "Username not found";
        }
        if(fournisseur.getUsername().equals(login.getUsername())){
            if(fournisseur.getMdp().equals(login.getMdp())){
                boolean haveMotif = restTemplate.getForObject("http://motif-service/motif/havemotif/"+fournisseur.getUsername(), boolean.class);
                if(haveMotif == true)
                    return "Compte inaccessible";
                return "Connexion done";
            }
            else
            {
                return "Incorrect password";
            }
        }
        return "Username not found";
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