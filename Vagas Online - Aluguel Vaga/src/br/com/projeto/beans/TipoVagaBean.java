package br.com.projeto.beans;

import java.io.Serializable;

public class TipoVagaBean implements Serializable {

	private static final long serialVersionUID = -3018273354763082864L;

	private int id;
	private int preco;
	private String nome;
	private int idEstacionamento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPreco() {
		return preco;
	}
	public void setPreco(int preco) {
		this.preco = preco;
	}
	public int getIdEstacionamento() {
		return idEstacionamento;
	}
	public void setIdEstacionamento(int idEstacionamento) {
		this.idEstacionamento = idEstacionamento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idEstacionamento;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + preco;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoVagaBean other = (TipoVagaBean) obj;
		if (id != other.id)
			return false;
		if (idEstacionamento != other.idEstacionamento)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (preco != other.preco)
			return false;
		return true;
	}
	
}