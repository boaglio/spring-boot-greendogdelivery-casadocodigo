package com.boaglio.casadocodigo.greendogdelivery.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ExemploAppRunner implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(ExemploAppRunner.class);

	@Override
    public void run(ApplicationArguments args) {
        logger.info("==== ExemploAppRunner ====");
        var debug = args.containsOption("debug");
        var outrosValores = args.getNonOptionArgs();
        logger.info("Chamou com debug? "+ debug);
        logger.info(String.valueOf(outrosValores));
        logger.info("==== ExemploAppRunner ====");
    }
}