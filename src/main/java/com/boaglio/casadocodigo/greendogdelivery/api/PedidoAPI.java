package com.boaglio.casadocodigo.greendogdelivery.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.boaglio.casadocodigo.greendogdelivery.domain.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.repository.ClienteRepository;
import com.boaglio.casadocodigo.greendogdelivery.repository.ItemRepository;
import com.boaglio.casadocodigo.greendogdelivery.repository.PedidoRepository;

@RestController
@RequestMapping("/api")
public class PedidoAPI {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ItemRepository itemRepository;

	public PedidoAPI(PedidoRepository pedidoRepository,ClienteRepository clienteRepository,ItemRepository itemRepository) {
		this.pedidoRepository = pedidoRepository;
		this.clienteRepository = clienteRepository;
		this.itemRepository = itemRepository;
	}

	@PostMapping("/pedido")
	public Pedido fazPedido(@RequestBody NovoPedido novoPedido) {
		
		Pedido pedido = new Pedido();
		Optional<Cliente> clienteOpt = clienteRepository.findById(novoPedido.getIdCliente());
		pedido.setCliente(clienteOpt.get());
		pedido.setData(new Date());
		pedido.setValorTotal(novoPedido.getValorTotal());
		
		List<Item> itens = new ArrayList<>();
		for (Long idItem : novoPedido.getItensId()) {
			Optional<Item> itemOpt = itemRepository.findById(idItem);
			Item item = itemOpt.get();
			itens.add(item);
		}
		pedido.setItens(itens);
		pedido.setStatus(FluxoPedido.CHEGOU_NA_COZINHA.name());
		
		pedidoRepository.save(pedido);
		
		return pedido;
	}

	@GetMapping("/pedido/{id}")
	public Pedido buscaPedido(@PathVariable Long id) {

		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if (pedidoOpt.isPresent()) {
			return pedidoOpt.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,FluxoPedido.NAO_ENCONTRADO.name());
		}
	}

	@DeleteMapping("/pedido/{id}")
	public void cancelaPedido(@PathVariable Long id) {
		
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		Pedido pedido = pedidoOpt.get();
		if (!pedidoOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,FluxoPedido.NAO_ENCONTRADO.name());
		}
		
		pedido.setStatus(FluxoPedido.CANCELADO.name());
		pedidoRepository.save(pedido);
		pedidoRepository.flush();
		 
	}

	@GetMapping("/pedido/all")
	public List<Pedido> buscaTudo() {

		List<Pedido> pedidoLista = pedidoRepository.findAll();
		if (pedidoLista.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}

		return pedidoLista;
	}
	
}
