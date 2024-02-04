package com.boaglio.casadocodigo.greendogdelivery.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = true)
	private Cliente cliente;

	@ManyToMany
	@Cascade(CascadeType.MERGE)
	private List<Item> itens;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date data;

	@Min(1)
	private Double valorTotal;

	public Pedido() {}

	public Pedido(Long id,Cliente cliente,List<Item> itens,Double valorTotal) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.itens = itens;
		this.data = new Date();
		this.valorTotal = valorTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "Pedido{" +
				"id=" + id +
				", cliente=" + cliente +
				", itens=" + itens +
				", data=" + data +
				", valorTotal=" + valorTotal +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pedido pedido = (Pedido) o;
		return Objects.equals(id, pedido.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
