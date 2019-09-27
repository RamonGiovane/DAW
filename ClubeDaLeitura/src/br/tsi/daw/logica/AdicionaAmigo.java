package br.tsi.daw.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tsi.daw.dao.AmigoDAO;
import br.tsi.daw.modelo.Amigo;

public class AdicionaAmigo implements Logica{

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Amigo amigo = new Amigo();
		amigo.setNome(request.getParameter("nome"));
		amigo.setTelefone(request.getParameter("telefone"));
		
		AmigoDAO dao =  new AmigoDAO();
		dao.adiciona(amigo);
		
		response.sendRedirect("menu.jsp");
	}
	
}
