package br.tsi.daw.logica;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tsi.daw.dao.EmprestimoDAO;
import br.tsi.daw.dao.RevistaDAO;
import br.tsi.daw.modelo.Emprestimo;

public class AdicionaEmprestimo implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Emprestimo emprestimo = new Emprestimo();
		Calendar data = Calendar.getInstance();
		data.add(Calendar.DATE, 10);
		emprestimo.setDataEmprestimo(Calendar.getInstance());
		emprestimo.setDataDevolucao(data);
		emprestimo.setIdAmigo(Long.parseLong(request.getParameter("amigo")));
		emprestimo.setIdRevista(Long.parseLong(request.getParameter("revista")));
		
		
		RevistaDAO revistaDAO = new RevistaDAO();
		if(!revistaDAO.verificarAmigo(emprestimo.getIdAmigo())) {
			response.sendRedirect("erro-revista-indisponivel.jsp");
			return;
		}
		
		
		EmprestimoDAO dao = new EmprestimoDAO();
		dao.adicionar(emprestimo);
	}
	
}
