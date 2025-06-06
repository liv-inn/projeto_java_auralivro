
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
    public String listarLivros(Model model) {
        model.addAttribute("livros", livroService.listarLivros());
        return "livros/listar";
    }

    @GetMapping("/novo")
    public String formNovoLivro(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("autores", autorService.listarAutores());
        return "livros/form";
    }

    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroService.salvarLivro(livro);
        return "redirect:/livros";
    }

    @GetMapping("/editar/{id}")
    public String formEditarLivro(@PathVariable int id, Model model) {
        model.addAttribute("livro", livroService.buscarLivroPorId(id));
        model.addAttribute("autores", autorService.listarAutores());
        return "livros/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable int id) {
        livroService.deletarLivro(id);
        return "redirect:/livros";
    }

    @GetMapping("/por-autor/{autorId}")
    public String listarPorAutor(@PathVariable int autorId, Model model) {
        model.addAttribute("livros", livroService.buscarLivrosPorAutor(autorId));
        model.addAttribute("autor", autorService.buscarAutorPorId(autorId));
        return "livros/listar-por-autor";
    }
}