package com.loja.BackLoja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.loja")
public class BackLojaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackLojaApplication.class, args);
	}

}
