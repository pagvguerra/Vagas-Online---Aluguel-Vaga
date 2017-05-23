package br.com.projeto.beans;

import java.io.Serializable;

public class FuncionarioBean  extends UsuarioBean implements Serializable{

	private static final long serialVersionUID = -9076179533530348490L;
	private int idEstacionamento;
	
	public FuncionarioBean() {
		super();
	}

	public int getIdEstacionamento() {
		return idEstacionamento;
	}

	public void setIdEstacionamento(int idEstacionamento) {
		this.idEstacionamento = idEstacionamento;
	}

}