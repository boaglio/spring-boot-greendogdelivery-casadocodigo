package com.boaglio.casadocodigo.greendogdelivery.service;

import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.queue.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AtualizaEstoque {

    public AtualizaEstoque(Producer producer, ObjectMapper mapper) {
        this.producer = producer;
        this.mapper = mapper;
    }

    private enum TIPOS_DE_METODO { 	REST, FILA, REST_REATIVO	}

	private final TIPOS_DE_METODO metodo = TIPOS_DE_METODO.REST_REATIVO;

	private static final String SERVER_CONTROLE_ESTOQUE = "http://localhost:9000";

	private static final String URL_ATUALIZA_ESTOQUE = SERVER_CONTROLE_ESTOQUE + "/api/atualiza";

	private static final String URI_ATUALIZA_ESTOQUE_REATIVO = "/api/atualiza-reativo";

	Logger logger = LoggerFactory.getLogger(AtualizaEstoque.class.getSimpleName());

	private final Producer producer;

	private final ObjectMapper mapper;

	public void processar(Pedido pedido) {

		try {

			logger.info(">>> " + metodo.name());

			switch (metodo) {

			case REST:

				var restClient = RestClient.create();

				for (Item item : pedido.getItens()) {

					String jsonEstoque = null;
					try {
						jsonEstoque = mapper.writeValueAsString(new Estoque(item.getId(), 1L));
					} catch (JsonProcessingException e) {
						logger.error("Erro:",e);
					}

					var headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					var request = new HttpEntity<String>(jsonEstoque, headers);
					logger.info("Request POST = " + request);

					var resultadoPost = restClient
							                           .post()
							                           .uri(URL_ATUALIZA_ESTOQUE)
							                           .body(request)
													   .retrieve().toEntity(String.class);

					logger.info("Resultado POST = " + resultadoPost);
				}

				break;

			case FILA:
				// atualiza estoque
				producer.send(pedido);
				break;

			case REST_REATIVO:

				for (Item item : pedido.getItens()) {

					var client = WebClient.create(SERVER_CONTROLE_ESTOQUE);

					var estoque = new Estoque(item.getId(), 1L);

					var atualizaReativo = client
							.post()
							.uri(URI_ATUALIZA_ESTOQUE_REATIVO)
							.body(Mono.just(estoque), Estoque.class)
							.retrieve()
							.bodyToMono(String.class);
					
					logger.info("Resultado POST reativo = ");
					atualizaReativo.subscribe(logger::info);

				}

				break;

			}
		} catch (Exception e) {
			logger.error("Erro:",e);
		}

	}

}
