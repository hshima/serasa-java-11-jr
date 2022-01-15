package br.com.shimada_henrique.serasajava11jr.model.repositories;

import br.com.shimada_henrique.serasajava11jr.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
