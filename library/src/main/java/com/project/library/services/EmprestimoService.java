package com.project.library.services;

import com.project.library.models.Emprestimo;
import com.project.library.DAOs.EmprestimoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoDAO emprestimoDAO;

    public void registrarEmprestimo(Emprestimo emprestimo) {
        emprestimoDAO.inserir(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimoDAO.listarTodos();
    }

    public Emprestimo buscarEmprestimoPorId(Long id) {
        return emprestimoDAO.buscarPorId(id);
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) {
        emprestimoDAO.atualizar(emprestimo);
    }

    public void registrarDevolucao(Long id) {
        emprestimoDAO.registrarDevolucao(id, LocalDate.now());
    }
}