package com.project.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.library.models.Livro;
import com.project.library.services.AutorService;
import com.project.library.services.LivroService;

@Controller
@RequestMapping("/livros")
public class LivroController {
    
    @Autowired
    private LivroService livroService;
    @Autowired
    private AutorService autorService;

    @GetMapping
    public String livros(Model model) {
        try {
            model.addAttribute("livros", livroService.listarLivros());
            model.addAttribute("livro", new Livro());
            model.addAttribute("autores", autorService.listarAutores());
            return "livros"; 
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("livros", java.util.Collections.emptyList());
            model.addAttribute("livro", new Livro());
            model.addAttribute("autores", java.util.Collections.emptyList());
            return "livros";
        }
    }

    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro) {
        try {
            livroService.salvarLivro(livro);
            return "redirect:/livros";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/livros?erro=true";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable Long id, Model model) {
        try {
            Livro livro = livroService.buscarLivroPorId(id);
            if (livro == null) {
                return "redirect:/livros";
            }
            model.addAttribute("livro", livro);
            model.addAttribute("livros", livroService.listarLivros());
            model.addAttribute("autores", autorService.listarAutores());
            return "livros";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/livros";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id) {
        try {
            livroService.deletarLivro(id);
            return "redirect:/livros";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/livros?erro=exclusao";
        }
    }
}