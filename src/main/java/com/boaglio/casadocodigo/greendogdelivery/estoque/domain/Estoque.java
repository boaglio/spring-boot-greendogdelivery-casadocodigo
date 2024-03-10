package com.boaglio.casadocodigo.greendogdelivery.estoque.domain;

import java.io.Serializable;
import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Estoque estoque = (Estoque) o;
		return Objects.equals(id, estoque.id) && Objects.equals(itemId, estoque.itemId) && Objects.equals(quantidade, estoque.quantidade);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemId, quantidade);
	}

	@Override
	public String toString() {
		return "Estoque [itemId=" + itemId + ", quantidade=" + quantidade + "]";
	}
	
}
