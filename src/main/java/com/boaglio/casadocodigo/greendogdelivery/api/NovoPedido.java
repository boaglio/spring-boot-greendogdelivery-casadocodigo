package com.boaglio.casadocodigo.greendogdelivery.api;

import java.util.List;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result + ((itensId == null) ? 0 : itensId.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NovoPedido other = (NovoPedido) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (itensId == null) {
			if (other.itensId != null)
				return false;
		} else if (!itensId.equals(other.itensId))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NovoPedido [idCliente=" + idCliente + ", itensId=" + itensId + ", valorTotal=" + valorTotal + "]";
	}
	
}
