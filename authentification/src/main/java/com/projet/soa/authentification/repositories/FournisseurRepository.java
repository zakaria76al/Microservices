package com.projet.soa.authentification.repositories;

import com.projet.soa.authentification.models.Fournisseur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FournisseurRepository extends MongoRepository<Fournisseur, Integer> {
}
