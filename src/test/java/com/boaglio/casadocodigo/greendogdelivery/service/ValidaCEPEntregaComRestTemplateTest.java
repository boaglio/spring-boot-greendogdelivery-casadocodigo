package com.boaglio.casadocodigo.greendogdelivery.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ValidaCEPEntregaComRestTemplateTest {

	@Autowired
	ValidaCEPEntregaComRestTemplate validaCEPEntrega;

	@Test
	public void testeCEPvalido() {

		var cepValidoParaEntrega = validaCEPEntrega.processa("49037475");

		assertThat(cepValidoParaEntrega, equalTo(true));

	}

	@Test
	public void testeCEPinvalido() {

		var cepValidoParaEntrega = validaCEPEntrega.processa("04101300");

		assertThat(cepValidoParaEntrega, equalTo(false));

	}

	@Test
	public void testeCEPqualquer() {
		var restTemplate = new RestTemplate();
		var cepURL = "https://viacep.com.br/ws/49037475/json";
		var response = restTemplate.getForEntity(cepURL, String.class);
		System.out.println("Response Status:"+response.getStatusCode());
		System.out.println("Response Body:");
		System.out.println(response.getBody());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}
}
