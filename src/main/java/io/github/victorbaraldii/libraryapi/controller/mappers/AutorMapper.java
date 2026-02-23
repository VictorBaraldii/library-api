package io.github.victorbaraldii.libraryapi.controller.mappers;

import io.github.victorbaraldii.libraryapi.controller.dto.AutorDTO;
import io.github.victorbaraldii.libraryapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);
    AutorDTO toDTO(Autor autor);
}
