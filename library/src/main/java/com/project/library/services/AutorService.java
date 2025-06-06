
package com.project.library.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.library.DAOs.AutorDAO;
import com.project.library.models.Autor;

@Service
public class AutorService {
    @Autowired
    private AutorDAO autorDAO;

    public void salvarAutor(Autor autor) {
        autorDAO.inserir(autor);
    }

    public List<Autor> listarAutores() {
        return autorDAO.listarTodos();
    }

    public Autor buscarAutorPorId(int id) {
        return autorDAO.buscarPorId(id);
    }

    public void atualizarAutor(Autor autor) {
        autorDAO.atualizar(autor);
    }

    public void deletarAutor(int id) {
        autorDAO.deletar(id);
    }
}