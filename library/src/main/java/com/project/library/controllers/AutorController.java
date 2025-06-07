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
    public String autores(Model model) {
        try {
            model.addAttribute("autores", autorService.listarAutores());
            model.addAttribute("autor", new Autor());
            return "autores"; 
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("autores", java.util.Collections.emptyList());
            model.addAttribute("autor", new Autor());
            return "autores";
        }
    }

    @PostMapping
    public String salvarAutor(@ModelAttribute Autor autor) {
        try {
            autorService.salvarAutor(autor);
            return "redirect:/autores";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/autores?erro=true";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarAutor(@PathVariable Long id, Model model) {
        try {
            Autor autor = autorService.buscarAutorPorId(id);
            if (autor == null) {
                return "redirect:/autores";
            }
            model.addAttribute("autor", autor);
            model.addAttribute("autores", autorService.listarAutores());
            return "autores";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/autores";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirAutor(@PathVariable Long id) {
        try {
            autorService.deletarAutor(id);
            return "redirect:/autores";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/autores?erro=exclusao";
        }
    }
}