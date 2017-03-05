package com.boaglio.casadocodigo.greendogdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boaglio.casadocodigo.greendogdelivery.domain.Item;

@Repository
//@RepositoryRestResource(collectionResourceRel = "itens",path = "itens")
public interface ItemRepository extends JpaRepository<Item, Long> {
 
}
