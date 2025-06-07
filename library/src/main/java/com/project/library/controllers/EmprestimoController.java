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
    public String emprestimos(Model model) {
        try {
            model.addAttribute("emprestimos", emprestimoService.listarEmprestimos());
            model.addAttribute("emprestimo", new Emprestimo());
            model.addAttribute("livros", livroService.listarLivros());
            return "emprestimos"; 
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("emprestimos", java.util.Collections.emptyList());
            model.addAttribute("emprestimo", new Emprestimo());
            model.addAttribute("livros", java.util.Collections.emptyList());
            return "emprestimos";
        }
    }

    @PostMapping
    public String registrarEmprestimo(@ModelAttribute Emprestimo emprestimo) {
        try {
            emprestimo.setDataEmprestimo(LocalDate.now());
            emprestimoService.registrarEmprestimo(emprestimo);
            return "redirect:/emprestimos";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/emprestimos?erro=true";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarEmprestimo(@PathVariable Long id, Model model) {
        try {
            Emprestimo emprestimo = emprestimoService.buscarEmprestimoPorId(id);
            if (emprestimo == null) {
                return "redirect:/emprestimos";
            }
            model.addAttribute("emprestimo", emprestimo);
            model.addAttribute("emprestimos", emprestimoService.listarEmprestimos());
            model.addAttribute("livros", livroService.listarLivros());
            return "emprestimos";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/emprestimos";
        }
    }

    @GetMapping("/devolver/{id}")
    public String registrarDevolucao(@PathVariable Long id) {
        try {
            emprestimoService.registrarDevolucao(id);
            return "redirect:/emprestimos";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/emprestimos?erro=devolucao";
        }
    }
}