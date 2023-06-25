package com.mrsystem.modelo.repository;

import com.mrsystem.modelo.entitys.FormaPagamento;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, UUID> {}
