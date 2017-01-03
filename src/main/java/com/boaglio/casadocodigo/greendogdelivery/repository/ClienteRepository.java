package com.boaglio.casadocodigo.greendogdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boaglio.casadocodigo.greendogdelivery.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

     
}
