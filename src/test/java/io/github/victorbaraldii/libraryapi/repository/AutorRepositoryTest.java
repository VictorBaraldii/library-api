package io.github.victorbaraldii.libraryapi.repository;

import io.github.victorbaraldii.libraryapi.model.Autor;
import io.github.victorbaraldii.libraryapi.model.GeneroLivro;
import io.github.victorbaraldii.libraryapi.model.Livro;
import net.minidev.json.annotate.JsonSmartAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1,12));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }
    @Test
    public void atualizarTest() {
        var id = UUID.fromString("cc35afbb-2f27-4451-9dea-a6bd1ff1faca");
        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);

        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("cc35afbb-2f27-4451-9dea-a6bd1ff1faca");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("e37dc38f-ba27-42fe-89e8-c7e7e46fd3d6");
        var teste1 = repository.findById(id).get();
        repository.delete(teste1);
    }

    @Test
    void salvarAutorComLivroTest() {
        Autor autor = new Autor();
        autor.setNome("Antónmio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1970, 8,5));

        Livro livro = new Livro();
        livro.setIsbn("90887-22");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Misterio livro");
        livro.setDataPublicacao(LocalDate.of(1999,1,2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("90887-0");
        livro2.setPreco(BigDecimal.valueOf(110));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("Misterio 2 livro");
        livro2.setDataPublicacao(LocalDate.of(2000,1,2));
        livro2.setAutor(autor);


        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());

    }

    @Test
    void listarAutor() {
        var id = UUID.fromString("3d9745f0-29cc-4e0e-a603-6b310e45ab59");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista =livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);

    }
}





















