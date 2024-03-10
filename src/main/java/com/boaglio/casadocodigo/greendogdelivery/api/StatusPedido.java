package com.boaglio.casadocodigo.greendogdelivery.api;

import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Objects;

@JsonInclude(Include.NON_NULL)
public class StatusPedido {
	
	private String status;
 
	private Pedido pedido;
	
	public StatusPedido() {}
	
	public StatusPedido(String status, Pedido pedido) {
		this.status = status;
		this.pedido = pedido;
	}

	public StatusPedido(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public String toString() {
		return "StatusPedido [status=" + status + ", pedido=" + pedido + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StatusPedido that = (StatusPedido) o;
		return Objects.equals(status, that.status) && Objects.equals(pedido, that.pedido);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, pedido);
	}
}
