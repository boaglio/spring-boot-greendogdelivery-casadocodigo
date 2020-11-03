package com.boaglio.casadocodigo.greendogdelivery.api;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteComRestTemplate {

	@Test
	public void testPedidoCompleto() throws Exception {

		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:8080/";

		// GET  /api/pedido/all
		ResponseEntity<String> responsePedidoAll = restTemplate.getForEntity(resourceUrl + "/api/pedido/all", String.class);
		
		assertThat(responsePedidoAll.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(responsePedidoAll.getBody(), containsString("Green Dog tradicional picante"));

		// POST /api/pedido/
		List<Long> itensId = List.of(2l);
		NovoPedido novoPedido = new NovoPedido(2l , itensId, 30.0);
		HttpEntity<NovoPedido> requestNovoPedido = new HttpEntity<>(novoPedido);
		
		Pedido responseNovoPedido = restTemplate.postForObject(resourceUrl + "/api/pedido/", requestNovoPedido, Pedido.class);
		
		Long idNovoPedido = responseNovoPedido.getId();
		assertThat(responseNovoPedido.getCliente().getId(), equalTo(novoPedido.getIdCliente()));
		assertThat(responseNovoPedido.getValorTotal(), equalTo(novoPedido.getValorTotal()));
		
		// GET /api/pedido/<idNovoPedido>
		ResponseEntity<Pedido> responseGetPedido = restTemplate.getForEntity(resourceUrl + "/api/pedido/"+idNovoPedido, Pedido.class);
		
		assertThat(responseGetPedido.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(responseGetPedido.getBody().getId(), equalTo(idNovoPedido));
		assertThat(responseGetPedido.getBody().getCliente().getId(), equalTo(responseNovoPedido.getCliente().getId()));
		
		// DELETE /api/pedido/<idNovoPedido>
		restTemplate.delete(resourceUrl + "/api/pedido/"+idNovoPedido); 
			
		// GET /api/pedido/<idNovoPedido>
		ResponseEntity<Pedido> responseGetPedidoRemovido = restTemplate.getForEntity(resourceUrl + "/api/pedido/"+idNovoPedido, Pedido.class);
		
		assertThat(responseGetPedidoRemovido.getBody().getStatus(), equalTo(FluxoPedido.CANCELADO.name()));
		
	}

}
