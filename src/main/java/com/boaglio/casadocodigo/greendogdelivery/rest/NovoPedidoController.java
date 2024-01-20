package com.boaglio.casadocodigo.greendogdelivery.rest;

import com.boaglio.casadocodigo.greendogdelivery.domain.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.dto.RespostaDTO;
import com.boaglio.casadocodigo.greendogdelivery.repository.ClienteRepository;
import com.boaglio.casadocodigo.greendogdelivery.repository.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController 
public class NovoPedidoController {

	public NovoPedidoController(ClienteRepository clienteRepository,ItemRepository itemRepository ) {
		this.clienteRepository =clienteRepository;
		this.itemRepository=itemRepository;
	}

	private final ClienteRepository clienteRepository;
	private final ItemRepository itemRepository;

	@GetMapping("/rest/pedido/novo/{clienteId}/{listaDeItens}")
	public RespostaDTO novo(@PathVariable("clienteId") Long clienteId,@PathVariable("listaDeItens") String listaDeItens) {

		var dto = new RespostaDTO();

		try {
			Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
			var c = clienteOpt.get();

			var listaDeItensID = listaDeItens.split(",");

			var pedido = new Pedido();

			var valorTotal = 0.0;
			List<Item> itensPedidos = new ArrayList<Item>();

			for (String itemId : listaDeItensID) {
				Optional<Item> itemOpt = itemRepository.findById(Long.parseLong(itemId));
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
			
			List<Long> pedidosID = new ArrayList<>();
			for (Pedido p : c.getPedidos()) {
				pedidosID.add(p.getId());
			}

			Long ultimoPedido = Collections.max(pedidosID);

			dto = new RespostaDTO(ultimoPedido,valorTotal,"Pedido efetuado com sucesso");

		} catch (Exception e) {
			dto.setMensagem("Erro: " + e.getMessage());
		}
		return dto;

	}

}