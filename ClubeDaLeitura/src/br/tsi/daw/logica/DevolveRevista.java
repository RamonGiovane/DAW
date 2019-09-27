package br.tsi.daw.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tsi.daw.dao.EmprestimoDAO;
import br.tsi.daw.modelo.Emprestimo;

public class DevolveRevista implements Logica{

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Emprestimo emprestimo = new Emprestimo();
		
		//emprestimo.setDataDevolucao(DAO.stringToCalendar(request.getParameter("datadevolucao")));
		//emprestimo.setDataEmprestimo(DAO.stringToCalendar(request.getParameter("dataemprestimo")));
		emprestimo.setId(Long.parseLong(request.getParameter("id")));
		emprestimo.setIdAmigo(Long.parseLong(request.getParameter("amigo")));
		emprestimo.setIdRevista(Long.parseLong(request.getParameter("revista")));

		EmprestimoDAO revistaDAO = new EmprestimoDAO();
		revistaDAO.devolver(emprestimo);
		
		response.sendRedirect("menu.jsp");
		
	}

	
}
