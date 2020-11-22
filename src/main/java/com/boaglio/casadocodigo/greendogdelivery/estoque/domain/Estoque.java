package com.boaglio.casadocodigo.greendogdelivery.estoque.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;


public class Estoque implements Serializable  {

	private static final long serialVersionUID = 2622999509067811332L;

	@Id
	private String id;
	
	private Long itemId;
	
	private Long quantidade;
	
	public Estoque(Long itemId, Long quantidade) {
		this.itemId = itemId;
		this.quantidade = quantidade;
	}

	public Estoque() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Estoque other = (Estoque) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estoque [itemId=" + itemId + ", quantidade=" + quantidade + "]";
	}
	
}
