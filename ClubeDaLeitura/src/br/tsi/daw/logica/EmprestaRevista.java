package br.tsi.daw.logica;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmprestaRevista implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("id", Long.parseLong(request.getParameter("id")));
		request.setAttribute("colecao", request.getParameter("colecao"));
		request.setAttribute("edicao", Integer.parseInt(request.getParameter("edicao")));
		RequestDispatcher rd = request.getRequestDispatcher("realizar-emprestimo.jsp");
		rd.forward(request, response);
	}
	
}
