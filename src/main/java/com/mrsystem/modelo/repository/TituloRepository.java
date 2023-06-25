package com.mrsystem.modelo.repository;

import com.mrsystem.modelo.entitys.Titulo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, UUID> {

    @Query("select max (numeroTitulo) from Titulo")
    Integer numeroUltimoTitulo();
}
