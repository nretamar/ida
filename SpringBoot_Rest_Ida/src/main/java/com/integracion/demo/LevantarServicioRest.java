package com.integracion.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.integracion.controlador")
public class LevantarServicioRest {

	public static void main(String[] args) {
		SpringApplication.run(LevantarServicioRest.class, args);
	}
}
