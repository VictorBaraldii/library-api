package io.github.victorbaraldii.libraryapi.service;

import io.github.victorbaraldii.libraryapi.model.Autor;
import io.github.victorbaraldii.libraryapi.model.GeneroLivro;
import io.github.victorbaraldii.libraryapi.model.Livro;
import io.github.victorbaraldii.libraryapi.repository.AutorRepository;
import io.github.victorbaraldii.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void salvarLivroComFoto() {
        // salva o livro
        // repository.save(livro)

        // pega o id do livro = livro.getId();
        // var id = livro.getId();

        // salvar foto do livro -> bucket na nuvem
        // bucketService.salvar(livro.getFoto(), id + ".png");

        // atualizar o nome arquivo que foi salvo
        // livro.setNomeArquivoFoto(id + ".png");
    }

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepository.findById(UUID.fromString("teste")).orElse(null);

        livro.setDataPublicacao(LocalDate.of(2004, 6, 1));

    }

    @Transactional
    public void executar() {

        Autor autor = new Autor();
        autor.setNome("Francisca");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1,12));

        autorRepository.save(autor);

        if(autor.getNome().equals("José")) {
            throw new RuntimeException("Rollback");
        }

        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Livro livro");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }
}
