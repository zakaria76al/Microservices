package com.projet.soa.authentification.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "fournisseur")
public class Fournisseur {
    @Id
    private int id;
    private String username;
    private String nom;
    private String adresse;
    private String tel;
    private String mdp;

    public Fournisseur() {
    }

    public Fournisseur(int id, String username, String nom, String adresse, String tel, String mdp) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public String getUsername() {
        return username;
    }

    public String getMdp() {
        return mdp;
    }
}
