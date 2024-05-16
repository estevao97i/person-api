package com.athenas.athenas.resources;

import com.athenas.athenas.service.PessoaService;
import com.athenas.athenas.service.dto.PessoaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.net.URI;

@RestController
@RequestMapping("/api/pessoa")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class PessoaResource {

    private final PessoaService service;

    @GetMapping()
    public ResponseEntity<List<PessoaDTO>> buscarTodos() {
        return ResponseEntity.ok().body(service.buscarTodos());
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<PessoaDTO> buscarPessoa(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok().body(service.buscaPessoa(cpf));
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<PessoaDTO> buscarPessoaPorNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok().body(service.buscaPessoaPorNome(nome));
    }

    @PostMapping()
    public ResponseEntity<List<PessoaDTO>> criar(@RequestBody PessoaDTO pessoa){
        var response = service.inserir(pessoa);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping()
    public ResponseEntity<PessoaDTO> atualizar(@RequestBody PessoaDTO pessoa) {
        PessoaDTO response = service.atualizar(pessoa);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf){
        service.apagarPorCpf(cpf);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/calcula-peso")
    public ResponseEntity<Double> calculaPesoIdeal(@RequestBody PessoaDTO pessoaDTO){
        return ResponseEntity.ok().body(service.calculaPesoIdeal(pessoaDTO));
    }

}
