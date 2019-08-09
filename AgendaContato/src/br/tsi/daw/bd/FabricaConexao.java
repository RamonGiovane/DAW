package br.tsi.daw.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost/agenda", "aluno", "aluno");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

}
