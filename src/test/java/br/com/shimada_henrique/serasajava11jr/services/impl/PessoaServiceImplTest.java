package br.com.shimada_henrique.serasajava11jr.services.impl;

import br.com.shimada_henrique.serasajava11jr.repositories.Pessoa;
import br.com.shimada_henrique.serasajava11jr.repositories.PessoaRepository;
import br.com.shimada_henrique.serasajava11jr.services.PessoaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PessoaServiceImplTest {

    PessoaService service;
    @Mock PessoaRepository repository;
    Pessoa pessoa;

    private Pessoa getPessoa() {
        return Pessoa.builder()
                .id(1L)
                .nome("Henrique")
                .cidade("São Paulo")
                .estado("São Paulo")
                .idade(31)
                .score(900)
                .telefone("123456789")
                .build();
    }

    @BeforeEach
    void setUp() {
        service = new PessoaServiceImpl(repository);
        pessoa = getPessoa();
    }

    @AfterEach
    void tearDown() {
        service = null;
        pessoa = null;
    }

    @Test
    void whenRequestsGetAllPessoa_thenReturnListOfPessoa() {
        when(repository.findAll()).thenReturn(List.of(pessoa));
        assertTrue(service.getAllPessoa().isPresent());
        assertEquals(service.getAllPessoa().get(), List.of(pessoa));
    }

}

