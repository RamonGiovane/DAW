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
	public String adicionaTarefa(@Valid Tarefa tarefa, BindingResult result, Model model) {

		// O bindingResult é uma classe capaz de armazenar erros que ocorreram na  validação
		//@Valid e que está configurado na classe Tarefa.java	
		if(result.hasFieldErrors("descricao")) {
			return "tarefas/novaTarefa";
		}
		dao = new TarefaDAO();
		dao.adiciona(tarefa);
		model.addAttribute("lista", dao.getLista());
		return "tarefas/lista";
	}

	//Método que lista todas as tarefas.
	@RequestMapping("/listarTarefas")
	public String lista(Model model) {
		dao = new TarefaDAO();
		model.addAttribute("lista", dao.getLista());

		return "tarefas/lista";
	}
	
	@RequestMapping("/finalizarTarefa")
	public String finalizarTarefa(Tarefa tarefa) {
		dao = new TarefaDAO();
		dao.finaliza(tarefa.getId());
		
		return "redirect:listarTarefas";
	}

	//Método que remove uma determinada tarefa.
	@RequestMapping("/removerTarefa")
	public String remove(Tarefa tarefa) {
		dao = new TarefaDAO();
		dao.removeTarefa(tarefa.getId());
		
		return "redirect:listarTarefas";
	}
	
	//Método que exibe uma determinada tarefa.
	@RequestMapping("/mostrarTarefa")
	public String mostrarTarefa(Tarefa tarefa, Model model) {
		dao = new TarefaDAO();
		Tarefa dadosTarefa = dao.buscaPorId(tarefa.getId());
		
		model.addAttribute("tarefa", dadosTarefa);
			
		return "tarefas/mostrarTarefa";
		
	}
	
	//Método que exibe uma determinada tarefa.
		@RequestMapping("/alterarTarefa")
		public String alterarTarefa(Tarefa tarefa) {
			dao = new TarefaDAO();
			dao.altera(tarefa);
			
			return "redirect:listarTarefas";
			
		}
}