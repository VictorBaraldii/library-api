package io.github.victorbaraldii.libraryapi.repository;

import io.github.victorbaraldii.libraryapi.model.Autor;
import io.github.victorbaraldii.libraryapi.model.GeneroLivro;
import io.github.victorbaraldii.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {

    Page<Livro> findByAutor(Autor autor, Pageable pageable);

    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    Optional<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    @Query("SELECT l FROM Livro l ORDER BY l.titulo, l.preco")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    @Query("SELECT a FROM Livro l JOIN l.autor a")
    List<Autor> listarAutoresDosLivros();

    @Query("SELECT DISTINCT l.titulo FROM Livro l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
            SELECT l.genero
            FROM Livro l
            JOIN l.autor a
            WHERE a.nacionalidade = 'Brasileira'
            ORDER BY l.genero
            """)
    List<String> listarGeneroAutoresBrasileiros();

    @Query("SELECT l FROM Livro l WHERE l.genero = :genero ORDER BY l.titulo")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro);

    @Modifying
    @Transactional
    @Query("DELETE FROM Livro WHERE genero = ?1")
    void deleteByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query("UPDATE Livro SET dataPublicacao = ?1")
    void updateDataPublicacao(LocalDate novaData);

    boolean existsByAutor(Autor autor);

}
