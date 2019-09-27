package br.tsi.daw.logica;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.tsi.daw.dao.AmigoDAO;
import br.tsi.daw.modelo.Emprestimo;

public class BuscaAmigo implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AmigoDAO amigoDAO = new AmigoDAO();
		List<Emprestimo>  emprestimos = amigoDAO.pesquisa(request.getParameter("nome"));
		
		request.setAttribute("emprestimos", emprestimos);
		RequestDispatcher rd = request.getRequestDispatcher("listar-amigo.jsp");
		rd.forward(request, response);
		
		
	}
	
}
