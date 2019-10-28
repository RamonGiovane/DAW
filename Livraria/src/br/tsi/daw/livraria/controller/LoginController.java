package br.tsi.daw.livraria.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.tsi.daw.livraria.dao.UsuarioDAO;
import br.tsi.daw.livraria.modelo.Carrinho;
import br.tsi.daw.livraria.modelo.TipoUsuario;
import br.tsi.daw.livraria.modelo.Usuario;

@Controller
public class LoginController extends br.tsi.daw.livraria.controller.Controller{

	private UsuarioDAO dao;

	
	
	public LoginController() {
		super();
		dao = new UsuarioDAO();
	}

	@RequestMapping("/formLogin")
	public String formLogin(Model model) {	
		adicionarCategorias(model);
		return "livraria/login";
	}

	@RequestMapping("/formSignup")
	public String formSignup(Model model) {	
		adicionarCategorias(model);
		return "livraria/signup";
	}

	@RequestMapping("/efetuarLogin")
	public String efetuaLogin(@RequestParam("usuario") String login, @RequestParam("senha")String senha, HttpSession session) {
		
		dao = new UsuarioDAO();
		
		Usuario user = dao.validaCredencial(login, senha);
		if(user != null) {
			System.out.println(user.getLogin());
			session.setMaxInactiveInterval(60);
			session.setAttribute("usuarioLogado", user);
			session.setAttribute("carrinho", new Carrinho());
			
			return "redirect:inicio";
		}
		System.out.println("erro");
		return "redirect:formLogin";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {	
		try{
			session.invalidate();
			session.setAttribute("usuarioLogado", null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:formLogin";
	}
	
	@RequestMapping("/signup")
	public String efetuaCadastro(@RequestParam("usuario") String login, @RequestParam("senha")String senha, HttpSession session) {	
		TipoUsuario tipoUsuario;
		try{
			tipoUsuario = ((Usuario) session.getAttribute("usuarioLogado")).getTipoUsuario();
			if(tipoUsuario == TipoUsuario.GERENTE || tipoUsuario == TipoUsuario.ADMIN)
				tipoUsuario = TipoUsuario.GERENTE;
			else
				tipoUsuario = TipoUsuario.USER;
		}catch (Exception e) {
			tipoUsuario = TipoUsuario.USER;
		}
		
		
		Usuario user = 	new Usuario(login, senha, tipoUsuario);
		if(!dao.adiciona(user)) {
			session.setAttribute("erroCadastro", true);
			return "redirect:formSignup";
		}
		efetuaLogin(login, senha, session);
		session.setAttribute("erroCadastro", false);
		return "redirect:inicio";
	}

}