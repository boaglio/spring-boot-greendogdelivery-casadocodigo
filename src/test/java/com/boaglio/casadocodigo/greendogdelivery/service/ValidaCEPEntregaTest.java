package com.boaglio.casadocodigo.greendogdelivery.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidaCEPEntregaTest {
	
	@Autowired
	ValidaCEPEntrega validaCEPEntrega;
	
	@Test
	public void testeCEPvalido() {
	   
		boolean cepValidoParaEntrega = validaCEPEntrega.processa("49037475");
		
		assertThat(cepValidoParaEntrega,equalTo(true));

	}
	
	@Test
	public void testeCEPinvalido() {
	   
		boolean cepValidoParaEntrega = validaCEPEntrega.processa("04101300");
		
		assertThat(cepValidoParaEntrega,equalTo(false));

	}
	
}
