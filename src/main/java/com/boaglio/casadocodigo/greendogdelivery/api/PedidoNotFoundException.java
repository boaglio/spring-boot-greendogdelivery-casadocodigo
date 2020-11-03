package com.boaglio.casadocodigo.greendogdelivery.api;

public class PedidoNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 164689864692931365L;

	PedidoNotFoundException(Long id) {
		super("Pedido " + id + " não encontrado");
	}

}
