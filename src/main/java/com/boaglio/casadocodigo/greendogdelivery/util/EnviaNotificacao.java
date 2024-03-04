package com.boaglio.casadocodigo.greendogdelivery.util;

import com.boaglio.casadocodigo.greendogdelivery.domain.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.dto.Notificacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EnviaNotificacao {

    private final Notificacao  notificacao;

	Logger logger = LoggerFactory.getLogger(EnviaNotificacao.class.getSimpleName());

    public EnviaNotificacao(Notificacao notificacao) {
        this.notificacao = notificacao;
    }

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