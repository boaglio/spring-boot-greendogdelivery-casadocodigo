package com.boaglio.casadocodigo.greendogdelivery.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.casadocodigo.greendogdelivery.repository.ClienteRepository;
import com.boaglio.casadocodigo.greendogdelivery.repository.ItemRepository;

@RestController("/rest/pedido")
public class NovoPedidoController {
 
	@Autowired
    private ClienteRepository clienteRepository;
	
	@Autowired
    private ItemRepository itemRepository;
	
	@GetMapping("/novo")
	public void novo() {

	}
	
}
