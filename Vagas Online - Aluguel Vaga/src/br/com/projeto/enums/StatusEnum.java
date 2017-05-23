package br.com.projeto.enums;

public enum StatusEnum {

	PENDENTE(0), 
	EM_PROGRESSO(1), 
	ATIVO(2);
	
	StatusEnum(int codigoOpcao){
		codigo = codigoOpcao;
	}

	private final int codigo;

	public int getCodigo(){
		return codigo;
	}
	
}
