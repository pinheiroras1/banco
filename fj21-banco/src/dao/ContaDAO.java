package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Conta;
import model.ContaCorrente;

import jdbc.ConnectionFactory;

public class ContaDAO {
	final int LIMITE_ZERO = 0;
	private Connection connection;
	final String insert = "insert into conta (titular,saldo,tipo,limite) values (?,?,?,?)";
	final String byId   = "select * from conta where id = ?";
	final String deleteTitular = "delete from conta where titular = ?";
	final String update = "update conta set limite=? where titular = ? and id = ?";
	
	public ContaDAO(){
		connection = new ConnectionFactory().getConnection();
	}
	
	public void add(Conta conta){
		try {
			PreparedStatement stms = connection.prepareStatement(insert);
			stms.setInt(1, conta.getTitular());
			stms.setDouble(2, conta.getSaldo());
			stms.setInt(3,conta.getTipo());
			if (conta.getTipo() == conta.CONTA_CORRENTE) { 
				stms.setDouble(4, ((ContaCorrente) conta).getLimite());
			}
			else {
				stms.setDouble(4, LIMITE_ZERO);
			}
			stms.execute();
			stms.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ContaCorrente byId(int id){
		try {
			PreparedStatement stms = connection.prepareStatement(byId);
			stms.setInt(1, id);
			ResultSet rs = stms.executeQuery();
			rs.next();
			ContaCorrente c = new ContaCorrente(0);
			c.setId(rs.getInt("id"));
			c.setTitular(rs.getInt("titular"));
			c.setSaldo(rs.getDouble("saldo"));
			c.setTipo(rs.getInt("tipo"));
			if (c.getTipo() == c.CONTA_CORRENTE) {
				((ContaCorrente) c).setLimite(rs.getDouble("limite"));
			}
			rs.close();
			stms.close();
			return c;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	public void remove(Conta conta) {
		try {
			PreparedStatement stms = connection.prepareStatement(deleteTitular);
			stms.setInt(1, conta.getTitular());
			stms.execute();
			stms.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public void update(ContaCorrente conta) {
		try {
			PreparedStatement smts = connection.prepareStatement(update);
			smts.setDouble(1,conta.getLimite());
			smts.setInt(2,conta.getTitular());
			smts.setInt(3, conta.getNumero());
			smts.execute();
			smts.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
