package br.tsi.daw.livraria.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{

	/*Responsável por validar a sessão do usuário. Dispensa o uso de imports em cada jsp para validar a sessão.
	 * Para isso, é necessário mapear esta classe, inserindo o seguinte em spring-context.xml:
	 * 	
	 * 	<mvc:interceptors>
			<bean class="br.tsi.daw.tarefas.interceptor.AutorizadorInterceptor"></bean>
		</mvc:interceptors>
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		String uri = request.getRequestURI();
		if(uri.endsWith("formLogin") || uri.endsWith("efetuarLogin") || uri.contains("resources") || !uri.contains("+"))
			return true;
		
		if(request.getSession().getAttribute("usuarioLogado") != null)
			return true;
		
		response.sendRedirect("formLogin");
		return false;
	}
	
	
	
}
