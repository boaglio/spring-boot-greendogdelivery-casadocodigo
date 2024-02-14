package com.boaglio.casadocodigo.greendogdelivery.api;

import java.io.Serial;

public class PedidoNotFoundException extends RuntimeException {
 
	@Serial
	private static final long serialVersionUID = 164689864692931365L;

	PedidoNotFoundException(Long id) {
		super("Pedido " + id + " não encontrado");
	}

}
