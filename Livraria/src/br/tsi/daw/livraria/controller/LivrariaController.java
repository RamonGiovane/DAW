package br.tsi.daw.livraria.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.tsi.daw.livraria.modelo.Carrinho;
import br.tsi.daw.livraria.modelo.Categoria;
import br.tsi.daw.livraria.modelo.Livro;

@Controller
public class LivrariaController extends br.tsi.daw.livraria.controller.Controller {


	@RequestMapping("/inicio")
	public String inicio(Model model) {

		adicionarCategorias(model);
		adicionarLivros(model);

		return "livraria/inicio";
	}
	
	
	
	@RequestMapping("/cadastrado")
	public String cadastroRealizado(Model model) {
		adicionarCategorias(model);

		return "livraria/cadastrado";
	}

	@RequestMapping("/formLivro")
	public String formCadastrarLivro(Model model) {

		adicionarCategorias(model);
		return "livraria/cadastrar-livro";
	}


	@RequestMapping("/formCategoria")
	public String formCadastrarCategoria(Model model) {

		adicionarCategorias(model);
		return "livraria/cadastrar-categoria";
	}

	@RequestMapping(value = "/cadastrar-livro", method = RequestMethod.POST)
	public String cadastrarLivro(Livro livro, @RequestParam("codCategoria") Long codCategoria, Model model, HttpSession session) {

		System.out.println("AAAA");
		if(livro.getDescricao().isEmpty() || livro.getTitulo().isEmpty() || livro.getTitulo().isEmpty() || livro.getPreco() == 0) {
			adicionarErroCadastro(session, true);
			return "redirect:formLivro";
		}

		livrariaDAO.adicionarLivro(livro, codCategoria);
		adicionarErroCadastro(session, false);

		return "redirect:cadastrado";
	}

	@RequestMapping(value = "/cadastrar-categoria", method = RequestMethod.POST)
	public String cadastrarLivro(Categoria categoria, Model model, HttpSession session) {


		if(categoria.getDescricao().isEmpty()) {
			adicionarErroCadastro(session, true);
			return "redirect:formCategoria";
		}

		livrariaDAO.adicionarCategoria(categoria);
		adicionarErroCadastro(session, false);

		return "redirect:cadastrado";
	}



	@RequestMapping("/buscarLivro")
	public String buscarLivros(@RequestParam("nomeLivro") String nomeLivro,	Model model) {

		model.addAttribute("lista", livrariaDAO.procurarLivros(nomeLivro));
		adicionarCategorias(model);
		return "livraria/listar-livros"; //deve retornar para uma lista
	}


	@RequestMapping("/buscarLivroPorCategoria")
	public String buscarLivros( @RequestParam("id") Long categoria, Model model) {
		adicionarCategorias(model);
		model.addAttribute("lista", livrariaDAO.procurarLivros(categoria));

		return "livraria/listar-livros"; //deve retornar para uma lista
	}

	@RequestMapping("/adicionarAoCarrinho+")
	public String addAoCarrinho(@RequestParam("codigo") Long codigoLivro, HttpSession session) {
		Livro livro = livrariaDAO.obterLivro(codigoLivro);
		

		Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

		if(verificarDisponiblidadeLivro(carrinho, livro)) {
			
			carrinho.adicionarLivro(livro);
			adicionarErroCadastro(session, false);
		}
		else
			adicionarErroCadastro(session, true);

		return "redirect:exibirCarrinho+";
	}

	private boolean verificarDisponiblidadeLivro(Carrinho carrinho, Livro livro) {
		for(Livro l : carrinho.getLivros()) {
			if(l.getCodigo() == livro.getCodigo()) {
				if(livro.getQuantidade() <= l.getQuantidade())
					return false;
				else
					return true;
			}
		}

		return true;
	}
	
	@RequestMapping("/pedidos+")
	public String obterPedidos(Model model, HttpSession session) {
		adicionarPedidos(model, session);
		adicionarCategorias(model);
		return "livraria/pedidos";
	}

	@RequestMapping("/removerDoCarrinho+")
	public String removerDoCarrinho(@RequestParam("codigo") Long codigoLivro, HttpSession session) {
		Livro livro = livrariaDAO.obterLivro(codigoLivro);
		obterCarrinho(session).removerLivro(livro);
		adicionarErroCadastro(session, false);
		return "redirect:exibirCarrinho+";
	}

	@RequestMapping("/fecharCarrinho+")
	public String fecharCarrinho(HttpSession session, Model model) {
		Carrinho  carrinho = obterCarrinho(session);
		if(!livrariaDAO.fecharCarrinho(obterCarrinho(session), session))
			return "livraria/inicio";

		carrinho.limparCarrinho();
		adicionarCategorias(model);
		return "livraria/sucesso";
	}

	@RequestMapping("/limparCarrinho+")
	public String limparCarrinho(HttpSession session) {
		obterCarrinho(session).limparCarrinho();
		return "redirect:exibirCarrinho+";
	}


	@RequestMapping("/exibirCarrinho+")
	public String exibirCarrinho(Model model, HttpSession session){
		Carrinho carrinho =  obterCarrinho(session);

		model.addAttribute("carrinho", carrinho.getLivros());
		model.addAttribute("valorTotal", carrinho.getValorTotal());
		adicionarCategorias(model);

		return "livraria/carrinho";
	}


}
