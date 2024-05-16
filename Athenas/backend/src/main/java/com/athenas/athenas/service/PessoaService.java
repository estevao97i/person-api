package com.athenas.athenas.service;

import com.athenas.athenas.exception.PessoaException;
import com.athenas.athenas.repository.Task;
import com.athenas.athenas.service.dto.PessoaDTO;
import com.athenas.athenas.service.mapper.PessoaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PessoaService {

    private final Task task;

    private final PessoaMapper mapper;

    public List<PessoaDTO> buscarTodos() {
        return mapper.toDto(task.findAll());
    }

    public PessoaDTO buscaPessoa(String cpf) {
        var pessoa = task.buscarPorCpf(cpf);
        if (pessoa == null) {
            throw new PessoaException("Pessoa não encontrada");
        }
        return mapper.toDto(pessoa);
    }

    public PessoaDTO buscaPessoaPorNome(String nome) {
        var pessoa = task.buscarPorNome(nome);
        if (pessoa == null) {
            throw new PessoaException("Pessoa não encontrada");
        }
        return mapper.toDto(pessoa);
    }

    public List<PessoaDTO> inserir(PessoaDTO pessoaDTO) {
        var pessoa = task.buscarPorCpf(pessoaDTO.getCpf());
        if (pessoa == null) {
            try {
                task.save(mapper.toEntity(pessoaDTO));
                return buscarTodos();
            } catch (Exception e) {
                throw new PessoaException("Não foi possível inserir.");
            }
        }
        throw new PessoaException("Pessoa com CPF já inserido.");
    }

    public PessoaDTO atualizar(PessoaDTO pessoaDTO) {
        var pessoa = task.buscarPorCpf(pessoaDTO.getCpf());
        if (pessoa != null) {
            pessoaDTO.setId(pessoa.getId());
            try {
                task.save(mapper.toEntity(pessoaDTO));
                return buscaPessoa(pessoaDTO.cpf);
            } catch (Exception e) {
                throw new PessoaException("Não foi possível atualizar.");
            }
        }
        throw new PessoaException("Não foi possível atualizar");

    }

    public void apagarPorCpf(String cpf) {
        var pessoa = task.buscarPorCpf(cpf);
        if (pessoa == null) {
            throw new PessoaException("Não foi possível apagar");
        }
        task.deleteById(pessoa.getId());
    }

    public Double calculaPesoIdeal(PessoaDTO pessoa) {
        return pessoa.calculaPesoIdeal();
    }
}
