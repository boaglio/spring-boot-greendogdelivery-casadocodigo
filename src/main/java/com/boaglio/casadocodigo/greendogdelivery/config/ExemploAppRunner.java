package com.boaglio.casadocodigo.greendogdelivery.config;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ExemploAppRunner implements ApplicationRunner {
    
	@Override
    public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("==== ExemploAppRunner ====");
        boolean debug = args.containsOption("debug");
        List<String> files = args.getNonOptionArgs();
        System.out.println("Chamou com debug? "+ debug);
        System.out.println(files);
        System.out.println("==== ExemploAppRunner ====");
    }
}