package io.github.victorbaraldii.libraryapi.validator;

import io.github.victorbaraldii.libraryapi.exceptions.RegistrosDuplicadosExceptions;
import io.github.victorbaraldii.libraryapi.model.Autor;
import io.github.victorbaraldii.libraryapi.repository.AutorRepository;
import org.aspectj.apache.bcel.classfile.Module.Open;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar(Autor autor) {
        if(existeAutorCadastrado(autor)) {
            throw new RegistrosDuplicadosExceptions("Autor já cadastrado!");
        }
    }

    private boolean existeAutorCadastrado(Autor autor) {
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade()
                );
        if(autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }

}
