package com.boaglio.casadocodigo.greendogdelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.queue.Producer;

@Component
public class AtualizaEstoque {

	@Autowired
	private Producer producer;
	
	public void processar(Pedido pedido) {
		
		try {
			// atualiza estoque 
			producer.send(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
