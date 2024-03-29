package br.tsi.daw.logica;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.tsi.daw.dao.EmprestimoDAO;
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
		
		
		EmprestimoDAO dao = new EmprestimoDAO();
		if(!dao.verificarAmigo(emprestimo.getIdAmigo())) {
			response.sendRedirect("erro.jsp");
			return;
		}
		
		try{
			dao.adicionar(emprestimo);
		}catch (Exception e) {
			response.sendRedirect("erro.jsp");
		}
		response.sendRedirect("menu.jsp");
	}
	
}
