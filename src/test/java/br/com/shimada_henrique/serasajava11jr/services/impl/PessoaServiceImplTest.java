package br.com.shimada_henrique.serasajava11jr.services.impl;

import br.com.shimada_henrique.serasajava11jr.model.Pessoa;
import br.com.shimada_henrique.serasajava11jr.model.dto.PessoaDto;
import br.com.shimada_henrique.serasajava11jr.model.repositories.PessoaRepository;
import br.com.shimada_henrique.serasajava11jr.services.PessoaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class PessoaServiceImplTest {

    PessoaService service;
    @Mock PessoaRepository repository;

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
    private PessoaDto getDto() {
        return PessoaDto.builder()
                .nome("Henrique")
                .cidade("São Paulo")
                .estado("São Paulo")
                .scoreDescricao("RECOMENDAVEL")
                .build();
    }

    @BeforeEach
    void setUp() {
        service = new PessoaServiceImpl(repository);
    }


    @AfterEach
    void tearDown() {
        service = null;
    }

    @Test
    void whenRequestsGetAllPessoa_thenReturnListOfPessoa() {
        when(repository.findAll()).thenReturn(List.of(getPessoa()));
        var listOfOptional = service.getAllPessoa();
        assertTrue(listOfOptional.isPresent());
        assertEquals(listOfOptional.get(), List.of(getDto()));
    }

}

