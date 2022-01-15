package br.com.shimada_henrique.serasajava11jr.controllers;


import br.com.shimada_henrique.serasajava11jr.repositories.Pessoa;
import br.com.shimada_henrique.serasajava11jr.repositories.PessoaRepository;
import br.com.shimada_henrique.serasajava11jr.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoa(){
        return service.getAllPessoa()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
