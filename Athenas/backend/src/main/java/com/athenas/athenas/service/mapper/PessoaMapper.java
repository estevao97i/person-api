package com.athenas.athenas.service.mapper;

import com.athenas.athenas.entity.Pessoa;
import com.athenas.athenas.service.dto.PessoaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PessoaMapper.class})
public interface PessoaMapper extends ParentMapper<PessoaDTO, Pessoa>{
}
