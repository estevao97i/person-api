package com.athenas.athenas.entity;

import com.athenas.athenas.enums.Sexo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Table(name = "pessoa_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "SeqA", sequenceName = "sq_tipo_a")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqA")
    public Long id;

    public String nome;

    @Temporal(TemporalType.DATE)
    public LocalDate dataNasc;

    @Column(length = 11, unique = true)
    public String cpf;

    public Sexo sexo;

    public Double altura;

    public Double peso;
}
