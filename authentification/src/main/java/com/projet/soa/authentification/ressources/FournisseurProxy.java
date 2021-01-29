package com.projet.soa.authentification.ressources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.projet.soa.authentification.models.Fournisseur;
import com.projet.soa.authentification.models.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
public class FournisseurProxy {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @HystrixCommand(fallbackMethod = "fallBackSaveFournisseur",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            })
    public String saveFournisseur(Fournisseur fournisseur)
    {
        return restTemplate.postForObject("http://FOURNISSEUR-SERVICE/addFournisseur", fournisseur, String.class);
    }

    public String fallBackSaveFournisseur(Fournisseur fournisseur){
        return "Server is busy";
    }

    @HystrixCommand(fallbackMethod = "fallBackLogin",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            })
    public String login(Login login) throws JsonProcessingException {
        final String uri = "http://FOURNISSEUR-SERVICE/findFournisseurByUser/" + login.getUsername();
        Fournisseur fournisseur = restTemplate.getForObject(uri, Fournisseur.class);// restTemplate.getForObject(uri, List.class);
        if(fournisseur.getId() == 0)
        {
            return "Username not found";
        }
        if(fournisseur.getUsername().equals(login.getUsername())){

            boolean correctMdp = bCryptPasswordEncoder.matches(login.getMdp(), fournisseur.getMdp());
            //boolean correctMdp = restTemplate.getForObject("http://FOURNISSEUR-SERVICE/checkPassword/" + login.getMdp(), Boolean.class);
            System.out.println(correctMdp);
            if(correctMdp){
                boolean haveMotif = restTemplate.getForObject("http://motif-service/motif/havemotif/" + fournisseur.getUsername(), boolean.class);
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

    public String fallBackLogin(Login login){
        return "Server is busy";
    }
}
