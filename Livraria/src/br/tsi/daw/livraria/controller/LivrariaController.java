package br.tsi.daw.livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.tsi.daw.livraria.dao.LivrariaDAO;

@Controller
public class LivrariaController {
	private LivrariaDAO dao; 
	
	
	
	public LivrariaController() {
		this.dao = new LivrariaDAO();
	}

	@RequestMapping("/inicio")
	public String listarCategorias(Model model) {
		
		//Model 
		model.addAttribute("lista", dao.getCategorias());

		return "livraria/inicio";
	}

	@RequestMapping("/buscarLivro")
	public String buscarLivro(String nomeLivro, Model model) {
		//string nomeLivro está vindo null
		model.addAttribute("lista", dao.getLivros(nomeLivro));

		return "redirect:livraria/listar-livros"; //deve retornar para uma lista
	}
	
}
