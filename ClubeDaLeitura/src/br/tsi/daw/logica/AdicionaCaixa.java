package br.tsi.daw.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tsi.daw.dao.RevistaDAO;
import br.tsi.daw.modelo.Caixa;

public class AdicionaCaixa  implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Caixa caixa = new Caixa();
		caixa.setCor(request.getParameter("cor"));
		
		RevistaDAO dao = new RevistaDAO();
		dao.adiciona(caixa);
		
		response.sendRedirect("menu.jsp");
	}
	
}
