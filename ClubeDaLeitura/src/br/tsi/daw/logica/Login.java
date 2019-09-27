package br.tsi.daw.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.tsi.daw.dao.UsuarioDAO;
import br.tsi.daw.modelo.Usuario;

public class Login implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setUsuario(request.getParameter("usuario"));
		usuario.setSenha(request.getParameter("senha"));
		
		UsuarioDAO dao =  new UsuarioDAO();
		if(dao.autentica(usuario)) {
			HttpSession session = request.getSession();
			session.setAttribute("logado", true);
			session.setMaxInactiveInterval(10);
			session.setAttribute("usuario", usuario);
			
			response.sendRedirect("menu.jsp");
		}
		
		response.sendRedirect("login.jsp");
	}
	
	
}
