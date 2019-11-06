package br.tsi.distribuidora.mb;

import javax.faces.bean.ManagedBean;

import br.tsi.distribuidora.dao.DAO;
import br.tsi.distribuidora.modelo.NotaFiscal;

@ManagedBean
public class NotaFiscalBean {
	private NotaFiscal notaFiscal = new NotaFiscal();
	
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	public void gravar() {
		DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
		dao.adiciona(notaFiscal);
		notaFiscal  = new NotaFiscal();
	}
	
}
