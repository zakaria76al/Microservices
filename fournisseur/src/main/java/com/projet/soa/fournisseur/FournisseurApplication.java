package com.projet.soa.fournisseur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FournisseurApplication {

	public static void main(String[] args) {
		SpringApplication.run(FournisseurApplication.class, args);
	}

}
