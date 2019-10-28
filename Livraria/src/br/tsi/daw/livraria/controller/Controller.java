package br.tsi.daw.livraria.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import br.tsi.daw.livraria.dao.LivrariaDAO;
import br.tsi.daw.livraria.modelo.Carrinho;
import br.tsi.daw.livraria.modelo.Usuario;

public abstract class Controller {
	protected LivrariaDAO livrariaDAO;
	
	public Controller() {
		this.livrariaDAO = new LivrariaDAO();
	}
	
	protected Carrinho obterCarrinho(HttpSession session) {
		return (Carrinho) session.getAttribute("carrinho");
	}

	protected void adicionarCategorias(Model model) {
		model.addAttribute("categorias", livrariaDAO.getCategorias());	
	}

	protected void adicionarLivros(Model model) {
		model.addAttribute("livros", livrariaDAO.getLivros());	
	}
	
	protected void adicionarErroCadastro(HttpSession session, boolean value) {
		session.setAttribute("erroCadastro", value);
	}
	
	protected void adicionarPedidos(Model model, HttpSession session) {
		model.addAttribute("pedidos", livrariaDAO.getPedidos((Usuario) session.getAttribute("usuarioLogado")));
	}
	
}
