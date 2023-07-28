package br.ufg.dlog.classes;

public enum Ativo {
	N("Nao"),
	S("Sim");
	
	private String nome;
	private Ativo(String nome) {
		this.nome=nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
