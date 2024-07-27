package br.com.fiap.hackaton.cartoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartoesApplication.class, args);
	}

}
