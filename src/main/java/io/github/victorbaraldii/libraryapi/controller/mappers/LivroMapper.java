package io.github.victorbaraldii.libraryapi.controller.mappers;

import io.github.victorbaraldii.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.victorbaraldii.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.victorbaraldii.libraryapi.model.Livro;
import io.github.victorbaraldii.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class )
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "genero", source = "generoLivro")
    @Mapping(
            target = "autor",
            expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )"
    )
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);
}
