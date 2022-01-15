package br.com.shimada_henrique.serasajava11jr.services.impl;

import br.com.shimada_henrique.serasajava11jr.repositories.Pessoa;
import br.com.shimada_henrique.serasajava11jr.repositories.PessoaRepository;
import br.com.shimada_henrique.serasajava11jr.services.PessoaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    private PessoaRepository repository;

    public PessoaServiceImpl(PessoaRepository repository){
        this.repository = repository;
    }

    public Optional<List<Pessoa>> getAllPessoa(){
        return Optional.of(repository.findAll());
    }
}
