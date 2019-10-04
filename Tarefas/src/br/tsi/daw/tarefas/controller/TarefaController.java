package br.tsi.daw.tarefas.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.tsi.daw.tarefas.dao.TarefaDAO;
import br.tsi.daw.tarefas.modelo.Tarefa;

@Controller
public class TarefaController {
	private TarefaDAO dao;

	public TarefaController() {

	}


	//Metodo que chama um formulario para cadastrar uma nova Tarefa
	@RequestMapping("/novaTarefa")
	public String formNovaTarefa() {
		return "tarefas/novaTarefa";
	}

	//Metodo que adiciona uma nova tarefa ao BD
	@RequestMapping("/adicionaTarefa")
	public String adicionaTarefa(@Valid Tarefa tarefa, BindingResult binding) {

		/*O BindingResult é uma classe capaz de armazena erros que ocorrerem
		 * na validação @Valid e que está configurado na classe Tarefa.java  
		 */
		System.out.println(binding.getAllErrors());
		if(binding.hasFieldErrors("descricao"))
			return "tarefas/novaTarefa";

		dao = new TarefaDAO();
		dao.adiciona(tarefa);
		return "tarefas/adicionada";
	}

	//Metodo que adiciona uma nova tarefa ao BD
	@RequestMapping("/lista")
	public String listaTarefa(Model model) {
		dao = new TarefaDAO();
		model.addAttribute("lista", dao.getLista());
		return "tarefas/lista";
	}
	
	
	
	



}
