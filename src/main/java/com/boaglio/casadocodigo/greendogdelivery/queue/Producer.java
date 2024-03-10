package com.boaglio.casadocodigo.greendogdelivery.queue;

import com.boaglio.casadocodigo.greendogdelivery.config.RabbitmqConfig;
import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	

	private final RabbitTemplate rabbitTemplate;

	Logger log = LoggerFactory.getLogger(Producer.class.getSimpleName());

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void send(Pedido pedido) throws Exception {
		
		for (Item item : pedido.getItens()) {

			log.info("Enviando mensagem - atualizando estoque - [ "+item.getNome()+" ] ...");
			
			var  estoqueMSG = new Estoque(item.getId(),1L);
			
			rabbitTemplate.convertAndSend(RabbitmqConfig.QUEUE_NAME, estoqueMSG);
			
		}
		
	}

}