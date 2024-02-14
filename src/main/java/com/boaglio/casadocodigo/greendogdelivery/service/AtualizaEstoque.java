package com.boaglio.casadocodigo.greendogdelivery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.rest.AtualizaEstoqueService;

@Component
public class AtualizaEstoque {

	@Autowired
	private AtualizaEstoqueService service;

	Logger log = LoggerFactory.getLogger(AtualizaEstoque.class.getSimpleName());

	public void processar(Pedido pedido) {
		
		try {
			// atualiza estoque 
			log.info("== Atualizar Estoque ==");
			service.send(pedido);
		} catch (Exception e) {
			log.error("Erro na atualização",e);
		}
		
	}
	
}
