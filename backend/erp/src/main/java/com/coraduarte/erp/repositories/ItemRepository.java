package com.coraduarte.erp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coraduarte.erp.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    Page<Item> findByNameContainingIgnoreCaseAndTipo(String nome, Integer tipo, Pageable pageable);

    Page<Item> findByNameContainingIgnoreCase(String nome, Pageable pageable);
}
