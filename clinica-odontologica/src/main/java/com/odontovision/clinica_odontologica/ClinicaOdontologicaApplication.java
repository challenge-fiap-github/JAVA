package com.odontovision.clinica_odontologica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Classe principal da aplicação da clínica odontológica.
 * <p>
 * Esta classe inicializa a aplicação Spring Boot e habilita o suporte a cache.
 * A anotação {@link SpringBootApplication} configura automaticamente a aplicação,
 * enquanto {@link EnableCaching} habilita o uso de cache na aplicação.
 * </p>
 */
@SpringBootApplication
@EnableCaching
public class ClinicaOdontologicaApplication {

	/**
	 * Método principal que inicia a aplicação.
	 *
	 * @param args Argumentos da linha de comando.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}
}
