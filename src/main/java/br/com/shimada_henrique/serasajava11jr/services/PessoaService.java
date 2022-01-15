package br.com.shimada_henrique.serasajava11jr.services;

import br.com.shimada_henrique.serasajava11jr.model.dto.PessoaDto;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    public Optional<List<PessoaDto>> getAllPessoa();
}
