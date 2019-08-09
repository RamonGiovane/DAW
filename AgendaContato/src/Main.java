import java.util.Calendar;

import br.tsi.daw.dao.ContatoDAO;
import br.tsi.daw.modelo.Contato;

public class Main {
	public static void main(String[] args) {
		Contato contato = new Contato();
		contato.setNome("Ramon");
		contato.setEmail("ramon@ada");
		contato.setEndereco("dsads");
		contato.setNascimento(Calendar.getInstance());
		
		ContatoDAO dao = new ContatoDAO();
		dao.adiciona(contato);
	}
}
