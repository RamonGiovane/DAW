package br.tsi.daw.livraria.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.tsi.daw.livraria.dao.LivrariaDAO;
import br.tsi.daw.livraria.modelo.Carrinho;
import br.tsi.daw.livraria.modelo.Livro;

@Controller
public class LivrariaController {
	private LivrariaDAO dao; 
	
	
	
	public LivrariaController() {
		this.dao = new LivrariaDAO();
	}

	@RequestMapping("/inicio")
	public String listarCategorias(Model model) {
		
		//Model 
		model.addAttribute("categorias", dao.getCategorias());

		return "livraria/inicio";
	}

	@RequestMapping("/buscarLivro")
	public String buscarLivros(@RequestParam("nomeLivro") String nomeLivro,	Model model) {

		model.addAttribute("lista", dao.procurarLivros(nomeLivro));
		adicionarCategorias(model);
		return "livraria/listar-livros"; //deve retornar para uma lista
	}


	@RequestMapping("/buscarLivroPorCategoria")
	public String buscarLivros( @RequestParam("id") Long categoria, Model model) {
		adicionarCategorias(model);
		model.addAttribute("lista", dao.procurarLivros(categoria));
				
		return "livraria/listar-livros"; //deve retornar para uma lista
	}
	
	@RequestMapping("/adicionarAoCarrinho")
	public String addAoCarrinho(@RequestParam("codigo") Long codigoLivro) {
		Livro livro = dao.obterLivro(codigoLivro);

		//		if(!checarLogin()) {
//			Carrinho.limparCarrinho();
//			if(!logar()) return "livraria/formLogin";
//		}
//	
			
		Carrinho.adicionarLivro(livro);
		return "redirect:exibirCarrinho";
	}
	
	@RequestMapping("/removerDoCarrinho")
	public String removerDoCarrinho(@RequestParam("codigo") Long codigoLivro) {
		Livro livro = dao.obterLivro(codigoLivro);
		Carrinho.removerLivro(livro);
		return "redirect:exibirCarrinho";
	}
	@RequestMapping("/fecharCarrinho")
	public String fecharCarrinho() {
		if(dao.fecharCarrinho())
			System.out.println("Success!"); //TODO: Mensagem decente
		else
			return "livraria/inicio";
		Carrinho.limparCarrinho();
		return "livraria/sucesso";
	}
	
	@RequestMapping("/limparCarrinho")
	public String limparCarrinho() {
		Carrinho.limparCarrinho();
		return "redirect:exibirCarrinho";
	}
	
	
	@RequestMapping("/exibirCarrinho")
	public String exibirCarrinho(Model model){
		
		model.addAttribute("carrinho", Carrinho.getLivros());
		model.addAttribute("valorTotal", Carrinho.getValorTotal());
		adicionarCategorias(model);
		return "livraria/carrinho";
	}

	private void adicionarCategorias(Model model) {
		model.addAttribute("categorias", dao.getCategorias());	
	}
}
