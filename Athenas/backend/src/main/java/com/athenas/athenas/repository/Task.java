package com.athenas.athenas.repository;

import com.athenas.athenas.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Task extends JpaRepository<Pessoa, Long> {

    @Query(" select p from Pessoa p where p.cpf = :cpf ")
    Pessoa buscarPorCpf(@Param("cpf") String cpf);

    @Query(" SELECT p from Pessoa p where p.nome = :nome ")
    Pessoa buscarPorNome(@Param("nome") String nome);

}
