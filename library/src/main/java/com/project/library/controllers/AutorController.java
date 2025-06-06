
package com.project.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.library.models.Autor;
import com.project.library.services.AutorService;

@Controller
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping
    public String listarAutores(Model model) {
        model.addAttribute("autores", autorService.listarAutores());
        return "autores/listar";
    }

    @GetMapping("/novo")
    public String formNovoAutor(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores/form";
    }

    @PostMapping
    public String salvarAutor(@ModelAttribute Autor autor) {
        autorService.salvarAutor(autor);
        return "redirect:/autores";
    }

    @GetMapping("/editar/{id}")
    public String formEditarAutor(@PathVariable int id, Model model) {
        model.addAttribute("autor", autorService.buscarAutorPorId(id));
        return "autores/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAutor(@PathVariable int id) {
        autorService.deletarAutor(id);
        return "redirect:/autores";
    }
}