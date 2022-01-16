package br.com.shimada_henrique.serasajava11jr.controllers;


import br.com.shimada_henrique.serasajava11jr.model.Pessoa;
import br.com.shimada_henrique.serasajava11jr.model.dto.PessoaDto;
import br.com.shimada_henrique.serasajava11jr.services.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("pessoas")
@Validated
public class PessoaController {

    @Autowired
    private IPessoaService service;

    @GetMapping
    public ResponseEntity<List<PessoaDto>> getAllPessoa(){
        return service.getAllPessoa()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> getPessoaById(@PathVariable("id") Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody @Valid Pessoa pessoa) {
        // Camada de transformação para garantir que não ocorra SQL Injection
        var validObject = PessoaDto.convert(pessoa);
        var doc = service.upsert(validObject);
        if(doc != null) {
            return ResponseEntity.created(URI.create("/pessoas/" + doc.getId())).build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
