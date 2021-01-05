package com.boaglio.casadocodigo.greendogdelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.queue.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class AtualizaEstoque {

	private enum TIPOS_DE_METODO {
		REST, FILA, REST_REATIVO;
	};

	private TIPOS_DE_METODO metodo = TIPOS_DE_METODO.REST_REATIVO;

	private static final String SERVER_CONTROLE_ESTOQUE = "http://localhost:9000";

	private static final String URL_ATUALIZA_ESTOQUE = SERVER_CONTROLE_ESTOQUE + "/api/atualiza";

	private static final String URI_ATUALIZA_ESTOQUE_REATIVO = "/api/atualiza-reativo";

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

			case REST_REATIVO:

				for (Item item : pedido.getItens()) {

					WebClient client = WebClient.create(SERVER_CONTROLE_ESTOQUE);

					Estoque estoque = new Estoque(item.getId(), 1l);

					Mono<String> atualizaReativo = client.post().uri(URI_ATUALIZA_ESTOQUE_REATIVO)
							.body(Mono.just(estoque), Estoque.class).retrieve().bodyToMono(String.class);
					
					System.out.print("Resultado POST reativo = ");
					atualizaReativo.subscribe(System.out::println);

				}

				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
