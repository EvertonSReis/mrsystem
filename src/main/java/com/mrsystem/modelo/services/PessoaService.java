package com.mrsystem.modelo.services;

import com.mrsystem.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;


}
