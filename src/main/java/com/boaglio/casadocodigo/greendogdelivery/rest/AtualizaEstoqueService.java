package com.boaglio.casadocodigo.greendogdelivery.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;

@Component
public class AtualizaEstoqueService {

	Logger log = LoggerFactory.getLogger(AtualizaEstoqueService.class.getSimpleName());

	public void send(Pedido pedido) {
		
		var restClient = RestClient.create();
		var resourceUrl = "http://localhost:9000/api/atualiza";
		
		for (Item item : pedido.getItens()) {

			log.info("Enviando requisicao - atualizando estoque - [ "+item.getNome()+" ] ...");
			
			var estoque = new Estoque(item.getId(),1l);

			var responseEstoque = restClient.post().uri(resourceUrl).body(estoque).retrieve().toEntity(String.class);

			log.info("Resposta da atualizaçao de estoque: "+responseEstoque.getBody());
			
		}
		
	}

}