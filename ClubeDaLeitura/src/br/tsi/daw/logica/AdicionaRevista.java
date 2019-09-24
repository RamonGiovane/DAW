package br.tsi.daw.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tsi.daw.dao.RevistaDAO;
import br.tsi.daw.modelo.Revista;

public class AdicionaRevista implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Revista revista = new Revista();
		revista.setAno(Integer.parseInt(request.getParameter("ano")));
		revista.setColecao(request.getParameter("colecao"));
		revista.setIdCaixa(Long.parseLong(request.getParameter("caixa")));
		revista.setNumeroEdicao(Integer.parseInt(request.getParameter("numero")));
		
		RevistaDAO dao = new RevistaDAO();
		dao.adiciona(revista);
		
		response.sendRedirect("menu.jsp");
		
	}
	
}
