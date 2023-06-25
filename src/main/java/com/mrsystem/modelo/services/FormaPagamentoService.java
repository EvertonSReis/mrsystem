package com.mrsystem.modelo.services;

import static java.lang.String.format;

import com.mrsystem.dtos.request.FormaPagamento.cadastro.CadastroFormaPagamentoDTO;
import com.mrsystem.dtos.response.FormaPagamento.RetornoFormaPagamentoDTO;
import com.mrsystem.excecoes.MRSystemRuntimeException;
import com.mrsystem.excecoes.ValidationException;
import com.mrsystem.modelo.builders.FormaPagamentoBuilder;
import com.mrsystem.modelo.entitys.FormaPagamento;
import com.mrsystem.modelo.enums.EValidacao;
import com.mrsystem.modelo.repository.FormaPagamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FormaPagamentoService {

    @Autowired FormaPagamentoRepository formaPagamentoRepository;

    @Autowired FormaPagamentoBuilder formaPagamentoBuilder;

    public RetornoFormaPagamentoDTO cadastrar(CadastroFormaPagamentoDTO formaPagamentoDTO) {
        try {

            FormaPagamento pagamento =
                    formaPagamentoBuilder.parserCadastroFormaPagamento(formaPagamentoDTO);

            return formaPagamentoBuilder.builderRetornoFormaPagamento(
                    formaPagamentoRepository.save(pagamento));
        } catch (ValidationException ex) {
            throw ex;
        } catch (Exception e) {
            log.error(format("Ocorreu um erro ao cadastrar a forma de pagamento."), e);
            throw new MRSystemRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
    }
}
