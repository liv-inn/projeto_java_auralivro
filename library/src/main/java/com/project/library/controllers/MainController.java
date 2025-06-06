

package com.project.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.library.services.AutorService;
import com.project.library.services.EmprestimoService;
import com.project.library.services.LivroService;

@Controller
public class MainController {

    @Autowired
    private AutorService autorService;
    
    @Autowired
    private LivroService livroService;
    
    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("autoresCount", autorService.listarAutores().size());
        model.addAttribute("livrosCount", livroService.listarLivros().size());
        
        long emprestimosAtivos = emprestimoService.listarEmprestimos().stream()
            .filter(e -> e.getDataDevolucao() == null)
            .count();
        model.addAttribute("emprestimosCount", emprestimosAtivos);
        
        return "index";
    }
}