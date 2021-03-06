package model;

public class Conta {

	public final int CONTA_CORRENTE = 0;
	public final int CONTA_POUPANCA = 1;
	
	private int numero;
	private Cliente titular;
	protected double saldo;	
	private int tipo; 
	
	public Conta(){
		titular = new Cliente();
	}
	
	public void deposito(double valor){
		if (valor > 0) this.saldo += valor;
	}
	
	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}
		
	public int getTitular(){
		return titular.getId();
	}

	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo){
		this.tipo = tipo;
	}

	public void setId(int numero) {
		this.numero = numero;
		
	}

	public void setTitular(int id) {
		titular.setId(id);
	}
	
	public void setSaldo(double saldo){
		this.saldo = saldo;
	}
}