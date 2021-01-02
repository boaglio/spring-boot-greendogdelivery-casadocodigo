package com.boaglio.casadocodigo.greendogdelivery.rest;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;

@Component
public class AtualizaEstoqueService {
	
	public void send(Pedido pedido) throws Exception { 
		
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:9000/api/atualiza";
		
		for (Item item : pedido.getItens()) {
			
			System.out.println("Enviando requisicao - atualizando estoque - [ "+item.getNome()+" ] ...");			
			
			Estoque estoque = new Estoque(item.getId(),1l);
			
			HttpEntity<Estoque> requestEstoque = new HttpEntity<>(estoque);
			
			String responseEstoque = restTemplate.postForObject(resourceUrl, requestEstoque, String.class);
			
			System.out.println("Resposta da atualizaçao de estoque: "+responseEstoque);
			
		}
		
	}

}