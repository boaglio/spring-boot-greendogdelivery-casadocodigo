package com.boaglio.casadocodigo.greendogdelivery.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.domain.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.dto.Notificacao;

@Component
public class EnviaNotificacao {

    @Autowired
    Notificacao  notificacao;
    
	public void enviaEmail(Cliente cliente,Pedido pedido) {
		
		System.out.println("Enviar notificacao para "+cliente.getNome() + " - pedido $"+pedido.getValorTotal());
		
		if (notificacao.envioAtivo()) {
			
			/*
			     codigo de envio
			 */
			
			System.out.println("Notificacao enviada!");
			
		} else {
			
			System.out.println("Notificacao desligada!");
		
		}
	}
	
	
}
