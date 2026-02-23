package io.github.victorbaraldii.libraryapi.controller.mappers;

import io.github.victorbaraldii.libraryapi.controller.dto.UsuarioDTO;
import io.github.victorbaraldii.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
