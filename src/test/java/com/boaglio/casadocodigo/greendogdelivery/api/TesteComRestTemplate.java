package com.boaglio.casadocodigo.greendogdelivery.api;

import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TesteComRestTemplate {

	@Autowired
	private ServerProperties serverProperties;

	@Test
	@DisplayName("Teste de Pedido Completo")
	public void testPedidoCompleto() throws Exception {

		var restTemplate = new RestTemplate();
		var resourceUrl = "http://localhost:"+serverProperties.getPort();

		// GET  /api/pedido/all
		var responsePedidoAll = restTemplate.getForEntity(resourceUrl + "/api/pedido/all", String.class);

		assertThat(responsePedidoAll.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(responsePedidoAll.getBody(), containsString("Green Dog tradicional picante"));

		// POST /api/pedido/
		var  itensId = List.of(2L);
		var novoPedido = new NovoPedido(2L , itensId, 30.0);
		var requestNovoPedido = new HttpEntity<>(novoPedido);

		var responseNovoPedido = restTemplate.postForObject(resourceUrl + "/api/pedido", requestNovoPedido, Pedido.class);

		var idNovoPedido = responseNovoPedido.getId();
		assertThat(responseNovoPedido.getValorTotal(), equalTo(novoPedido.getValorTotal()));

		// GET /api/pedido/<idNovoPedido>
		var responseGetPedido = restTemplate.getForEntity(resourceUrl + "/api/pedido/"+idNovoPedido, Pedido.class);

		assertThat(responseGetPedido.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(responseGetPedido.getBody().getId(), equalTo(idNovoPedido));

		// DELETE /api/pedido/<idNovoPedido>
		restTemplate.delete(resourceUrl + "/api/pedido/"+idNovoPedido);

		// GET /api/pedido/<idNovoPedido>
		var responseGetPedidoRemovido = restTemplate.getForEntity(resourceUrl + "/api/pedido/"+idNovoPedido, Pedido.class);

		assertThat(responseGetPedidoRemovido.getBody().getStatus(), equalTo(FluxoPedido.CANCELADO.name()));

	}

}
