package br.com.shimada_henrique.serasajava11jr.services.impl;

import br.com.shimada_henrique.serasajava11jr.model.dto.PessoaDto;
import br.com.shimada_henrique.serasajava11jr.model.repositories.PessoaRepository;
import br.com.shimada_henrique.serasajava11jr.services.PessoaService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    public PessoaServiceImpl(PessoaRepository repository){
        this.repository = repository;
    }

    public Optional<List<PessoaDto>> getAllPessoa(){
        var resp = repository.findAll();
        if(resp.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(
                    resp.stream()
                            .filter(Objects::nonNull)
                            .map(PessoaDto::new)
                            .collect(Collectors.toList())
            );
        }
    }

    @Override
    public Optional<PessoaDto> findById(Long id) {
        return repository.findById(id).map(PessoaDto::new);
    }
}
