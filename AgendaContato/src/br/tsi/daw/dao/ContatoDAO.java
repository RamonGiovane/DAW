package br.tsi.daw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.tsi.daw.bd.FabricaConexao;
import br.tsi.daw.modelo.Contato;

public class ContatoDAO {
	private Connection connection;
	
	public ContatoDAO() {
		this.connection = FabricaConexao.getConnection();
		
	}
	
	public void altera(Contato contato) {
		String sql = "update contato set nome = ?, email=?, endereco = ?, nascimento = ?"
				+ " where id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setString(1, contato.getNome());
			preparedStatement.setString(2, contato.getEmail());
			preparedStatement.setString(3, contato.getEndereco());
			preparedStatement.setDate(4, new Date(contato.getNascimento().getTimeInMillis()));
			preparedStatement.setLong(5, contato.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void adiciona(Contato contato) {
		String sql = "insert into contato (nome, email, endereco, nascimento)"
				+ " values (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setString(1, contato.getNome());
			preparedStatement.setString(2, contato.getEmail());
			preparedStatement.setString(3, contato.getEndereco());
			preparedStatement.setDate(4, new Date(contato.getNascimento().getTimeInMillis()));
			
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		public void remove(Contato contato) {
			String sql = "delete from contato where id = ?";
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setLong(1, contato.getId());
				
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public List<Contato> listaContatos(){
			List<Contato> contatos = new ArrayList<Contato>();
			String sql = "select * from contato";
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					Contato contato = new Contato();
					contato.setId(rs.getLong("id"));
					contato.setNome(rs.getString("nome"));
					contato.setEmail(rs.getString("email"));
					contato.setEndereco(rs.getString("endereco"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("nascimento"));
					
					contato.setNascimento(data);
					
				}
				preparedStatement.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return contatos;
			
		}
}
