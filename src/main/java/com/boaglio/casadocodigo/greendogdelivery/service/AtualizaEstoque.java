package com.boaglio.casadocodigo.greendogdelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.rest.AtualizaEstoqueService;

@Component
public class AtualizaEstoque {

	@Autowired
	private AtualizaEstoqueService service;
	
	public void processar(Pedido pedido) {
		
		try {
			// atualiza estoque 
			System.out.println("== Atualizar Estoque ==");
			service.send(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
