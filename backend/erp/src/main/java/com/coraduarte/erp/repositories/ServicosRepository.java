package com.coraduarte.erp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coraduarte.erp.models.Servicos;

@Repository
public interface ServicosRepository  extends JpaRepository<Servicos, Long>{
   
    //@Query(value = "SELECT s FROM Servicos s WHERE s.cliente.id = : id")
    //List<Servicos> xyz( @Param ("id") Long id);

    @Query(value = "SELECT * FROM services s WHERE t.cliente_id = :id", nativeQuery = true)
    List<Servicos> findByCliente_Id(@Param("id") Long id);
}
