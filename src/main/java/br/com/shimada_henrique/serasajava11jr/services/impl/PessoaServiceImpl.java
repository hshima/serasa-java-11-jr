package br.com.shimada_henrique.serasajava11jr.services.impl;

import br.com.shimada_henrique.serasajava11jr.model.Pessoa;
import br.com.shimada_henrique.serasajava11jr.model.dto.PessoaDto;
import br.com.shimada_henrique.serasajava11jr.model.repositories.PessoaRepository;
import br.com.shimada_henrique.serasajava11jr.services.IPessoaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements IPessoaService {

    private final PessoaRepository repository;

    public PessoaServiceImpl(PessoaRepository repository){
        this.repository = repository;
    }

    @Override
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

    @Override
    public Pessoa upsert(Pessoa pessoa){
        return repository.findByNomeAndTelefone(pessoa.getNome(), pessoa.getTelefone())
                .map(value -> repository.save(
                        Pessoa.builder()
                                .id(value.getId())
                                .nome(value.getNome())
                                .telefone(value.getTelefone())
                                .score(pessoa.getScore())
                                .cidade(pessoa.getCidade())
                                .estado(pessoa.getEstado())
                                .idade(pessoa.getIdade())
                                .build())
                ).orElseGet(() -> repository.save(pessoa));
    }
}
