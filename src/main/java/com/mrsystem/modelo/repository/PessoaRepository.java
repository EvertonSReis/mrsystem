package com.mrsystem.modelo.repository;

import com.mrsystem.modelo.entitys.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query("select count(*) > 0 " +
            "from Pessoa p " +
            "where p.cpfCnpj = :cpfCnpj")
    boolean existeCadastroParaCpfCnpj(Integer cpfCnpj);

    @Query("select max(p.idPessoa) from Pessoa p")
    Integer ultimoCodigoCliente();
}
