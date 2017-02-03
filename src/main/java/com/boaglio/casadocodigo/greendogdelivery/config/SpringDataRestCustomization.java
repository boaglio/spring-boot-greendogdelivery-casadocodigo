package com.boaglio.casadocodigo.greendogdelivery.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.repository.ClienteRepository;

@Component
public class SpringDataRestCustomization 
extends RepositoryRestConfigurerAdapter 
{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Item.class,ClienteRepository.class);
	}
}
