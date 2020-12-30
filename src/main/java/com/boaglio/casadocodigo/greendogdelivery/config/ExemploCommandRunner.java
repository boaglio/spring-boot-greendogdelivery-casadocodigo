package com.boaglio.casadocodigo.greendogdelivery.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExemploCommandRunner implements CommandLineRunner {
    
	@Override
    public void run(String ... args) throws Exception {
		
		System.out.println("==== ExemploCommandRunner ====");
        
		for (String arg : args)
         System.out.println("["+arg+"]");
		
        System.out.println("==== ExemploCommandRunner ====");
    }
}