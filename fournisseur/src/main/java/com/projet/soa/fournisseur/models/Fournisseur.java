package com.projet.soa.fournisseur.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "fournisseur")
public class Fournisseur {
    @Id @Generated
    private int id;
    @NonNull
    @Indexed(unique = true)
    private String Username;
    @NonNull
    private String Nom;
    @NonNull
    private String Adresse;
    @NonNull
    private String Tel;
    @NonNull
    private String Mdp;
    @NonNull
    private String Email;

    public Fournisseur() {
    }

    public Fournisseur(int id, @NonNull String username, @NonNull String nom, @NonNull String adresse, @NonNull String tel, @NonNull String mdp, @NonNull String email) {
        this.id = id;
        Username = username;
        Nom = nom;
        Adresse = adresse;
        Tel = tel;
        Mdp = mdp;
        Email = email;
    }

    public String getNom() {
        return Nom;
    }

    public int getId() {
        return id;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getTel() {
        return Tel;
    }

    public String getUsername() {
        return Username;
    }

    public String getMdp() {
        return Mdp;
    }

    public String getEmail() {
        return Email;
    }

    public void setMdp(String mdp) {
        Mdp = mdp;
    }
}
