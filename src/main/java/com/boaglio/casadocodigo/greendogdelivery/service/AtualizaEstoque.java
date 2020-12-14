package com.boaglio.casadocodigo.greendogdelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.queue.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AtualizaEstoque {

	private enum TIPOS_DE_METODO {
		REST, FILA;
	};

	private TIPOS_DE_METODO metodo = TIPOS_DE_METODO.REST;

	private static final String URL_ATUALIZA_ESTOQUE = "http://localhost:9000/api/atualiza";

	@Autowired
	private Producer producer;

	@Autowired
	private ObjectMapper mapper;

	public void processar(Pedido pedido) {

		try {

			System.out.println(">>> " + metodo.name());

			switch (metodo) {

			case REST:

				RestTemplate restTemplate = new RestTemplate();

				for (Item item : pedido.getItens()) {

					String jsonEstoque = null;
					try {
						jsonEstoque = mapper.writeValueAsString(new Estoque(item.getId(), 1l));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}

					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					HttpEntity<String> request = new HttpEntity<>(jsonEstoque, headers);
					System.out.println("Request POST = " + request.toString());

					String resultadoPost = restTemplate.postForObject(URL_ATUALIZA_ESTOQUE, request, String.class);
					System.out.println("Resultado POST = " + resultadoPost);
				}

				break;

			case FILA:
				// atualiza estoque
				producer.send(pedido);
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
