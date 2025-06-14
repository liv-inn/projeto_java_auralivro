
package com.project.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.DAOs.LivroDAO;
import com.project.library.models.Livro;

@Service
public class LivroService {
    @Autowired
    private LivroDAO livroDAO;

    
    public void salvarLivro(Livro livro) {
        if (livro.getId() == null) {
            livroDAO.inserir(livro);
        } else {
            livroDAO.atualizar(livro);
        }
    }

    public List<Livro> listarLivros() {
        return livroDAO.listarTodos();
    }

    public Livro buscarLivroPorId(Long id) {
        return livroDAO.buscarPorId(id);
    }

    public void atualizarLivro(Livro livro) {
        livroDAO.atualizar(livro);
    }

    public void deletarLivro(Long id) {
        livroDAO.deletar(id);
    }

    public List<Livro> buscarLivrosPorAutor(int autorId) {
        return livroDAO.buscarPorAutor(autorId);
    }
}