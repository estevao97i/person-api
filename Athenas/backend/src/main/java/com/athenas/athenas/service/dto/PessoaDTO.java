package com.athenas.athenas.service.dto;

import com.athenas.athenas.entity.Pessoa;
import com.athenas.athenas.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO implements Serializable {

    public Long id;

    public String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date dataNasc;

    public String cpf;

    public Sexo sexo;

    public Double altura;

    public Double peso;

    public Double calculaPesoIdeal(){
        if(this.sexo.equals(Sexo.F)){
            return ((62.1*this.altura) -44.7);
        }
        return ((72.7*this.altura) -58);
    }

}
