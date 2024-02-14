package com.boaglio.casadocodigo.greendogdelivery.rest;

import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.dto.RespostaDTO;
import com.boaglio.casadocodigo.greendogdelivery.repository.ClienteRepository;
import com.boaglio.casadocodigo.greendogdelivery.repository.ItemRepository;
import com.boaglio.casadocodigo.greendogdelivery.service.AtualizaEstoque;
import com.boaglio.casadocodigo.greendogdelivery.util.EnviaNotificacao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@RestController 
public class NovoPedidoController {


	public NovoPedidoController(ClienteRepository clienteRepository,ItemRepository itemRepository,EnviaNotificacao enviaNotificacao,AtualizaEstoque atualizaEstoque) {
		this.clienteRepository =clienteRepository;
		this.itemRepository=itemRepository;
		this.enviaNotificacao = enviaNotificacao;
		this.atualizaEstoque = atualizaEstoque;
	}

	private final ClienteRepository clienteRepository;
	private final ItemRepository itemRepository;
	private final EnviaNotificacao enviaNotificacao;
	private final AtualizaEstoque atualizaEstoque;

	@GetMapping("/rest/pedido/novo/{clienteId}/{listaDeItens}")
	public RespostaDTO novo(@PathVariable("clienteId") Long clienteId,@PathVariable("listaDeItens") String listaDeItens) {

		RespostaDTO dto;
 	    double valorTotal = 0.0;
		long ultimoPedido = 0;
		try {

			var clienteOpt = clienteRepository.findById(clienteId);
			var c = clienteOpt.get();
			var listaDeItensID = listaDeItens.split(",");
			var pedido = new Pedido();
			var itensPedidos = new ArrayList<Item>();

			for (String itemId : listaDeItensID) {

				var itemOpt = itemRepository.findById(Long.parseLong(itemId));
				var item = itemOpt.get();
				 
				itensPedidos.add(item);
				valorTotal += item.getPreco();
			}
			pedido.setItens(itensPedidos);
			pedido.setValorTotal(valorTotal);
			pedido.setData(new Date());
			pedido.setCliente(c);
			c.getPedidos().add(pedido);

			this.clienteRepository.saveAndFlush(c);
			
			enviaNotificacao.enviaEmail(c,pedido);

			var pedidosID = new ArrayList<Long>();
			for (Pedido p : c.getPedidos()) {
				pedidosID.add(p.getId());
			}

			ultimoPedido = Collections.max(pedidosID);

			// atualiza estoque
			atualizaEstoque.processar(pedido);
			
			dto = new RespostaDTO(ultimoPedido,valorTotal,"Pedido efetuado com sucesso");

		} catch (Exception e) {

			dto = new RespostaDTO(ultimoPedido,valorTotal,"Erro: " + e.getMessage());

		}
		return dto;

	}

}
