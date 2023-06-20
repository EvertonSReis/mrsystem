package com.mrsystem.modelo.repository;

import com.mrsystem.modelo.entitys.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query("select count(*) > 0 " +
            "from Pessoa p " +
            "where p.cpfCnpj = :cpfCnpj")
    boolean existeCadastroParaCpfCnpj(String cpfCnpj);

    @Query("select max(p.codigo) from Pessoa p")
    Integer ultimoCodigoCliente();

    Page<Pessoa> findAll(Pageable pageable);
}
