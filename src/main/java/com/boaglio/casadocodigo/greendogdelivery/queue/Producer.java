package com.boaglio.casadocodigo.greendogdelivery.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.config.RabbitmqConfig;
import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;

@Component
public class Producer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(Pedido pedido) throws Exception { 
		
		for (Item item : pedido.getItens()) {
			
			System.out.println("Enviando mensagem - atualizando estoque - [ "+item.getNome()+" ] ...");
			
			Estoque estoqueMSG = new Estoque(item.getId(),1l);
			
			rabbitTemplate.convertAndSend(RabbitmqConfig.QUEUE_NAME, estoqueMSG);
			
		}
		
	}

}