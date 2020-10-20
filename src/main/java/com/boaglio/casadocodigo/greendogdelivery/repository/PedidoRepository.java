package com.boaglio.casadocodigo.greendogdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
 
	 
}
