package com.boaglio.casadocodigo.greendogdelivery.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.domain.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.dto.Notificacao;

@Component
public class EnviaNotificacao {

    @Autowired
    Notificacao  notificacao;

	Logger logger = LoggerFactory.getLogger(EnviaNotificacao.class.getSimpleName());

	public void enviaEmail(Cliente cliente,Pedido pedido) {

		logger.info("Enviar notificacao para "+cliente.getNome() + " - pedido $"+pedido.getValorTotal());
		
		if (notificacao.envioAtivo()) {
			
			/*
			     codigo de envio
			 */

			logger.info("Notificacao enviada!");
			
		} else {

			logger.info("Notificacao desligada!");
		
		}
	}
	
	
}
