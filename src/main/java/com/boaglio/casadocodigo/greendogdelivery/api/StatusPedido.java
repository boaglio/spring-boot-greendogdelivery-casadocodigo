package com.boaglio.casadocodigo.greendogdelivery.api;

import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record StatusPedido(String status, Pedido pedido) {
}
