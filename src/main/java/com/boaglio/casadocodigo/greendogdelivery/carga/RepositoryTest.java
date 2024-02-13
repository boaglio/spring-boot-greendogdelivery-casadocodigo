package com.boaglio.casadocodigo.greendogdelivery.carga;

import com.boaglio.casadocodigo.greendogdelivery.domain.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryTest
		implements ApplicationRunner
{

	Logger logger = LoggerFactory.getLogger(ApplicationRunner.class.getSimpleName());

	private static final long ID_CLIENTE_FERNANDO = 11L;
	private static final long ID_CLIENTE_ZE_PEQUENO = 22L;

	private static final long ID_ITEM1 = 100L;
	private static final long ID_ITEM2 = 101L;
	private static final long ID_ITEM3 = 102L;

	private static final long ID_PEDIDO1 = 1000L;
	private static final long ID_PEDIDO2 = 1001L;
	private static final long ID_PEDIDO3 = 1002L;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

		logger.info(">>> Iniciando carga de dados...");
		Cliente fernando = new Cliente(ID_CLIENTE_FERNANDO,"Fernando Boaglio","Sampa");
		Cliente zePequeno = new Cliente(ID_CLIENTE_ZE_PEQUENO,"Zé Pequeno","Cidade de Deus");

		var dog1 = new Item(ID_ITEM1,"Green Dog tradicional",25d);
		var dog2 = new Item(ID_ITEM2,"Green Dog tradicional picante",27d);
		var dog3 = new Item(ID_ITEM3,"Green Dog max salada",30d);

		var listaPedidoFernando1 = new ArrayList<Item>();
		listaPedidoFernando1.add(dog1);

		var listaPedidoZePequeno1 = new ArrayList<Item>();
		listaPedidoZePequeno1.add(dog2);
		listaPedidoZePequeno1.add(dog3);

		Pedido pedidoDoFernando = new Pedido(ID_PEDIDO1,fernando,listaPedidoFernando1,dog1.getPreco());
		fernando.novoPedido(pedidoDoFernando);

		Pedido pedidoDoZepequeno = new Pedido(ID_PEDIDO2,zePequeno,listaPedidoZePequeno1, dog2.getPreco()+dog3.getPreco());
		zePequeno.novoPedido(pedidoDoZepequeno);

		logger.info(">>> Pedido 1 - Fernando : "+ pedidoDoFernando);
		logger.info(">>> Pedido 2 - Ze Pequeno: "+ pedidoDoZepequeno);

		clienteRepository.saveAndFlush(zePequeno);
		logger.info(">>> Gravado cliente 2: "+zePequeno);

		var listaPedidoFernando2 = new ArrayList<Item>();
		listaPedidoFernando2.add(dog2);
		var pedido2DoFernando  = new Pedido(ID_PEDIDO3,fernando,listaPedidoFernando2,dog2.getPreco());
		fernando.novoPedido(pedido2DoFernando);
		clienteRepository.saveAndFlush(fernando);
		logger.info(">>> Pedido 2 - Fernando : "+ pedido2DoFernando);
		logger.info(">>> Gravado cliente 1: "+fernando);

	}

}