package br.com.shimada_henrique.serasajava11jr.services;

import br.com.shimada_henrique.serasajava11jr.model.Pessoa;
import br.com.shimada_henrique.serasajava11jr.model.dto.PessoaDto;

import java.util.List;
import java.util.Optional;

public interface IPessoaService {

    public Optional<List<PessoaDto>> getAllPessoa();

    public Optional<PessoaDto> findById(Long id);

    public Pessoa upsert(Pessoa pessoa);
}
