package br.tsi.distribuidora.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.tsi.distribuidora.mb.UsuarioBean;

public class Autorizador implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent evento) {
		FacesContext contexto = evento.getFacesContext();
		if("/login.xhtml".equals(contexto.getViewRoot().getViewId())) {
			return;
		}
		
		UsuarioBean usuarioBean = contexto.getApplication().evaluateExpressionGet(contexto, "#{usuarioBean}", UsuarioBean.class);
		
		if(!usuarioBean.isUsuarioLogado()) {
			NavigationHandler handler = contexto.getApplication().getNavigationHandler();
			handler.handleNavigation(contexto, null, "login?faces-redirect=true");
			contexto.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}
