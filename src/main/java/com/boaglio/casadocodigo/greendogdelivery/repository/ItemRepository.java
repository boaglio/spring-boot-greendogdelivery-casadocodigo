package com.boaglio.casadocodigo.greendogdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	 @RestResource(path = "por-nome")
	 Pedido findByNome(@Param("nome") String nome);
	 
}
