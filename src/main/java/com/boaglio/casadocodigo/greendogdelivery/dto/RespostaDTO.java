package com.boaglio.casadocodigo.greendogdelivery.dto;

public class RespostaDTO {

	private Double valorTotal;

	private Long pedido;

	private String mensagem;

	public RespostaDTO(Long pedido,Double valorTotal,String mensagem) {
		super();
		this.pedido = pedido;
		this.valorTotal = valorTotal;
		this.mensagem = mensagem;
	}

	public RespostaDTO() {}
	
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getPedido() {
		return pedido;
	}

	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ( (pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ( (valorTotal == null) ? 0 : valorTotal.hashCode());
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
		RespostaDTO other = (RespostaDTO) obj;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
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
		return "RespostaDTO [pedido=" + pedido + ", valorTotal=" + valorTotal + ", mensagem=" + mensagem + "]";
	}

}
