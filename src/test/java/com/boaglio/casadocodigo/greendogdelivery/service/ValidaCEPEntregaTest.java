package com.boaglio.casadocodigo.greendogdelivery.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidaCEPEntregaTest {

	@Autowired
	ValidaCEPEntrega validaCEPEntrega;

	@Test
	public void testeCEPvalido() {

		boolean cepValidoParaEntrega = validaCEPEntrega.processa("49037475");

		assertThat(cepValidoParaEntrega, equalTo(true));

	}

	@Test
	public void testeCEPinvalido() {

		boolean cepValidoParaEntrega = validaCEPEntrega.processa("04101300");

		assertThat(cepValidoParaEntrega, equalTo(false));

	}

	@Test
	public void testeCEPqualquer() {
		RestTemplate restTemplate = new RestTemplate();
		String cepURL = "https://viacep.com.br/ws/49037475/json";
		ResponseEntity<String> response = restTemplate.getForEntity(cepURL, String.class);
		System.out.println("Response Status:"+response.getStatusCode());
		System.out.println("Response Body:");
		System.out.println(response.getBody());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}
}
