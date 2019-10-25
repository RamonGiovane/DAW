package br.tsi.daw.livraria.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.tsi.daw.livraria.dao.UsuarioDAO;
import br.tsi.daw.livraria.modelo.Carrinho;
import br.tsi.daw.livraria.modelo.Usuario;

@Controller
public class LoginController {

	private UsuarioDAO dao;

	@RequestMapping("/formLogin")
	public String formLogin() {	
		return "login";
	}

	@RequestMapping("/efetuarLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		
		dao = new UsuarioDAO();
		Usuario user = dao.validaCredencial(usuario.getLogin(), usuario.getSenha());
		if(user != null) {
			System.out.println(user.getLogin());
			session.setAttribute("usuarioLogado", user);
			session.setAttribute("carrinho", new Carrinho());
			return "redirect:listarTarefas";
		}
		System.out.println("erro");
		return "redirect:formLogin";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {	
		session.invalidate();
		return "redirect:formLogin";
	}

}