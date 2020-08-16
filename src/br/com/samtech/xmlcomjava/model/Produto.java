package br.com.samtech.xmlcomjava.model;

public class Produto {

	private String nome;
	private Double valor;

	public Produto() {
	}
	
	public Produto(String nome, Double valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}

	
	public String getNome() {
		return nome;
	}
	public Double getValor() {
		return valor;
	}
		
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.nome + " Preço: " + this.valor;
	}
	
}
