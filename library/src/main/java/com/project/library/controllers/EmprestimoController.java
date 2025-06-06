
package com.project.library.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.library.models.Emprestimo;
import com.project.library.services.EmprestimoService;
import com.project.library.services.LivroService;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;
    
    @Autowired
    private LivroService livroService;

    @GetMapping
    public String listarEmprestimos(Model model) {
        model.addAttribute("emprestimos", emprestimoService.listarEmprestimos());
        return "emprestimos/listar";
    }

    @GetMapping("/novo")
    public String formNovoEmprestimo(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livroService.listarLivros());
        return "emprestimos/form";
    }

    @PostMapping
    public String registrarEmprestimo(@ModelAttribute Emprestimo emprestimo) {
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimoService.registrarEmprestimo(emprestimo);
        return "redirect:/emprestimos";
    }

    @GetMapping("/devolver/{id}")
    public String registrarDevolucao(@PathVariable int id) {
        emprestimoService.registrarDevolucao(id);
        return "redirect:/emprestimos";
    }
}