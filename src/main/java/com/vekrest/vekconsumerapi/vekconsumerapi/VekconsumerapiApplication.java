package com.vekrest.vekconsumerapi.vekconsumerapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VekconsumerapiApplication implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(VekconsumerapiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VekconsumerapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("VEKREST -> VEKCONSUMERAPI - INICIALIZADO COM SUCESSO!");
	}
}
