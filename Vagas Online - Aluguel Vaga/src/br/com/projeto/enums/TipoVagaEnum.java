package br.com.projeto.enums;

public enum TipoVagaEnum {

	MOTO(1, "MOTO"), 
	CARRO(2, "CARRO"), 
	�NIBUS(3, "�NIBUS"),
	CAMINH�O(4, "CAMINH�O");
	
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
