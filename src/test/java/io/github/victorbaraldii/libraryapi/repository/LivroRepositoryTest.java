package io.github.victorbaraldii.libraryapi.repository;

import io.github.victorbaraldii.libraryapi.model.Autor;
import io.github.victorbaraldii.libraryapi.model.GeneroLivro;
import io.github.victorbaraldii.libraryapi.model.Livro;
import io.github.victorbaraldii.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Rollback(false)
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    TransacaoService service;

    @Test
    void salvarLivroTest() {

        Livro livro = new Livro();
        livro.setIsbn("90887-874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Misterio livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("69029b33-34d2-42b9-8fce-d96e4972e98a"))
                .orElseThrow();

        livro.setAutor(autor);

        repository.save(livro);
    }


    @Test
    void salvarAutorELivroTest() {
        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 12));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Terceiro livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("12345");
        livro.setPreco(BigDecimal.valueOf(80));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Livro com Cascade");
        livro.setDataPublicacao(LocalDate.of(1990, 1, 1));

        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1970, 5, 10));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void buscarLivroComAutor() {
        UUID id = repository.findAll().get(0).getId();
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("Outro livro");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbnTest() {
        Optional<Livro> livro = repository.findByIsbn("90887-84874");
        livro.ifPresent(System.out::println);
    }

    @Test
    void pesquisaPorTituloEPrecoTest() {
        var preco = BigDecimal.valueOf(100);
        var titulo = "Outro livro";

        List<Livro> lista = repository.findByTituloAndPreco(titulo, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosOrdenados() {
        repository.listarTodosOrdenadoPorTituloAndPreco()
                .forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
        repository.listarAutoresDosLivros()
                .forEach(System.out::println);
    }

    @Test
    void listarTitulosDiferentes() {
        repository.listarNomesDiferentesLivros()
                .forEach(System.out::println);
    }

    @Test
    void listarGenerosAutoresBrasileiros() {
        repository.listarGeneroAutoresBrasileiros()
                .forEach(System.out::println);
    }

    @Test
    void listarPorGenero() {
        repository.findByGenero(GeneroLivro.MISTERIO)
                .forEach(System.out::println);
    }

    @Test
    void deletePorGenero() {
        repository.deleteByGenero(GeneroLivro.MISTERIO);
    }

    @Test
    void updateDataPublicacao() {
        repository.updateDataPublicacao(LocalDate.of(2000, 1, 1));
    }
}
