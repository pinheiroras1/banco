package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Contato;

import jdbc.ConnectionFactory;

public class ContatoDAO {
	
	private Connection connection;
	
	final String insert = "insert into contato(nome,email,endereco,dataNascimento) values (?,?,?,?)";
	final String select = "select * from contato";
	final String byNome = "select * from contato where nome=?";
	final String delete = "delete from contato where id=?";
	final String update = "update contato set nome=?, email=?, endereco=?, dataNascimento=? where id=?";
	final String byId = "select * from contato where id=?";
	final String selectNomeLike = "select * from contato where nome like ?";

	
	
	public ContatoDAO(){
		connection = new ConnectionFactory().getConnection();
	}
	
	public void atualiza(Contato contato) throws SQLException{
		PreparedStatement stms = connection.prepareStatement(update);
		stms.setString(1, contato.getNome());
		stms.setString(2, contato.getEmail());
		stms.setString(3, contato.getEndereco());
		stms.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
		stms.setLong(5, contato.getId());
		stms.execute();
		stms.close();
	}
	
	public void adiciona(Contato contato){
		try {
			PreparedStatement stms = connection.prepareStatement(insert);
			stms.setString(1, contato.getNome());
			stms.setString(2, contato.getEmail());
			stms.setString(3, contato.getEndereco());
			stms.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()) );
			
			stms.execute();
			stms.close();
		} catch (SQLException e) {
			throw new RuntimeException(e); // Duvida aqui.
		}
	}

	public Contato pesquisa(Integer id){
		try {
			PreparedStatement stms = connection.prepareStatement(byId);
			stms.setInt(1, id);
			ResultSet rs = stms.executeQuery();
			Contato c = new Contato();
			rs.next();
			c = dadosBanco(rs);	
			rs.close();
			stms.close();
			return c;
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}
	}
	
	public Contato listaNome(Contato contato){
		try {
			PreparedStatement stms = connection.prepareStatement(byNome);
			stms.setString(1, contato.getNome());
			ResultSet rs = stms.executeQuery();
			Contato c = new Contato();
			rs.next();
			c = dadosBanco(rs);	
			rs.close();
			stms.close();
			return c;
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}
	}
	
	private Contato dadosBanco(ResultSet rs){
		Contato contato = new Contato();
		try {
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
			
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
			return contato;			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contato> getLista(){
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = connection.prepareStatement(select);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				contatos.add( (Contato) dadosBanco(rs) );	
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contato> getListaNomeLike(String nome){
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = connection.prepareStatement(selectNomeLike);
			stmt.setString(1, "%"+nome+"%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				contatos.add((Contato) dadosBanco(rs));
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void delete(Contato contato) throws SQLException{
		PreparedStatement stmt = connection.prepareStatement(delete);
		stmt.setLong(1, contato.getId());
		stmt.execute();
		stmt.close();
	}
	
}
