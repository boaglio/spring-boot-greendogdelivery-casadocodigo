package com.boaglio.casadocodigo.greendogdelivery.api;

import java.util.List;
import java.util.Objects;

public class NovoPedido {

	private Long idCliente;

	private List<Long> itensId;

	private Double valorTotal;

	public NovoPedido() {}
	
	public NovoPedido(Long idCliente, List<Long> itensId, Double valorTotal) {
		super();
		this.idCliente = idCliente;
		this.itensId = itensId;
		this.valorTotal = valorTotal;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<Long> getItensId() {
		return itensId;
	}

	public void setItensId(List<Long> itensId) {
		this.itensId = itensId;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NovoPedido that = (NovoPedido) o;
		return Objects.equals(idCliente, that.idCliente) && Objects.equals(itensId, that.itensId) && Objects.equals(valorTotal, that.valorTotal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCliente, itensId, valorTotal);
	}

	@Override
	public String toString() {
		return "NovoPedido [idCliente=" + idCliente + ", itensId=" + itensId + ", valorTotal=" + valorTotal + "]";
	}
	
}
