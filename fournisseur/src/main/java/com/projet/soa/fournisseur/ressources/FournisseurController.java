package com.projet.soa.fournisseur.ressources;

import com.projet.soa.fournisseur.models.Fournisseur;
import com.projet.soa.fournisseur.repositories.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FournisseurController {

    @Autowired
    private FournisseurRepository repository;

    @PostMapping("/addFournisseur")
    public String saveFournisseur(@RequestBody Fournisseur fournisseur){
        repository.save(fournisseur);
        return "Added with userName " + fournisseur.getUsername() + " And ID : " + fournisseur.getId();
    }

    @GetMapping("/findAllFournisseurs")
    public List<Fournisseur> getFournisseurs(){
        return repository.findAll();
    }

    @GetMapping("/findFournisseurByUser/{userName}")
    public Fournisseur getFournisseurByUser(@PathVariable String userName){
        List<Fournisseur> list = repository.findAll();
        for (Fournisseur a: list) {
            if(userName.equals(a.getUsername())){
                return a;
            }
        }
        return new Fournisseur();
    }

    @GetMapping("/findFournisseur/{id}")
    public Optional<Fournisseur> getFournisseur(@PathVariable int id){
        return repository.findById(id);
    }

    @GetMapping("/deleteFournisseur/{id}")
    public String deleteFournisseur(@PathVariable int id){
        repository.deleteById(id);
        return "Fournisseur deleted : " + id;
    }
}