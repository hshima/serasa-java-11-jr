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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        service.getAllPessoa()
                .ifPresent(pessoaDtos -> assertEquals(pessoaDtos, List.of(getDto())));
    }

    @Test
    void whenRequestsGetAllPessoaAndListIsEmpty_thenReturnNull() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(service.getAllPessoa().isEmpty());
    }

    @Test
    void whenRequestGetById_thenReturnsSingleDocument(){
        when(repository.findById(1L)).thenReturn(Optional.of(getPessoa()));
        service.findById(1L)
                .ifPresent(pessoaDto -> assertEquals(pessoaDto, getDto()));
    }

    @Test
    void whenRequestGetByIdForNonExistingDocumen_thenReturnsEmptyOptional(){
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertTrue(service.findById(1L).isEmpty());
    }

    @Test
    void whenRequestPostForNewPessoa_thenInsertsInDataBase(){
        when(repository.findByNomeAndTelefone("Henrique", "123456879")).thenReturn(Optional.empty());
        when(repository.save(getPessoa())).thenReturn(getPessoa());
        assertEquals(service.upsert(getPessoa()), getPessoa());
    }

    @Test
    void whenRequestPostForExistingPessoa_thenUpdatesInDataBase(){
        when(repository.findByNomeAndTelefone("Henrique", "123456879")).thenReturn(Optional.of(getPessoa()));
        when(repository.save(getPessoa())).thenReturn(getPessoa());
        assertEquals(service.upsert(getPessoa()), getPessoa());
    }

}

