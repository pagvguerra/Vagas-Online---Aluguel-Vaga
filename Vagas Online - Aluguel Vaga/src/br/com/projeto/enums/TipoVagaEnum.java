package br.com.projeto.enums;

public enum TipoVagaEnum {

	MOTO(1, "MOTO"), 
	CARRO(2, "CARRO"), 
	ÔNIBUS(3, "ÔNIBUS"),
	CAMINHÃO(4, "CAMINHÃO");
	
	TipoVagaEnum(int codigoOpcao, String nomeOpcao){
		codigo = codigoOpcao;
		nome = nomeOpcao;
	}

	private final int codigo;
	private String nome;

	public int getCodigo(){
		return codigo;
	}
	
	public String getNome(){
		return nome;
	}
	
	
}
