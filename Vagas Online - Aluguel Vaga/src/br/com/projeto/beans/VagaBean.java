package br.com.projeto.beans;

import java.io.Serializable;
import java.util.Date;

public class VagaBean implements Serializable {

	private static final long serialVersionUID = -254053325140513230L;

	private int idEstacionamento;
	private int id;
	private String codigo;
	private int largura;
	private int altura;
	private String status;
	private TipoVagaBean tipoVagaBean;
	private Date dataLocacaoVaga;
	private String dataLocacaoVagaStr;
	private Date dataLiberacaoVaga;
	private String dataLiberacaoVagaStr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getIdEstacionamento() {
		return idEstacionamento;
	}

	public void setIdEstacionamento(int idEstacionamento) {
		this.idEstacionamento = idEstacionamento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TipoVagaBean getTipoVagaBean() {
		return tipoVagaBean;
	}

	public void setTipoVagaBean(TipoVagaBean tipoVagaBean) {
		this.tipoVagaBean = tipoVagaBean;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDataLocacaoVaga() {
		return dataLocacaoVaga;
	}

	public void setDataLocacaoVaga(Date dataLocacaoVaga) {
		this.dataLocacaoVaga = dataLocacaoVaga;
	}

	public Date getDataLiberacaoVaga() {
		return dataLiberacaoVaga;
	}

	public void setDataLiberacaoVaga(Date dataLiberacaoVaga) {
		this.dataLiberacaoVaga = dataLiberacaoVaga;
	}
	
	public String getDataLocacaoVagaStr() {
		return dataLocacaoVagaStr;
	}

	public void setDataLocacaoVagaStr(String dataLocacaoVagaStr) {
		this.dataLocacaoVagaStr = dataLocacaoVagaStr;
	}

	public String getDataLiberacaoVagaStr() {
		return dataLiberacaoVagaStr;
	}

	public void setDataLiberacaoVagaStr(String dataLiberacaoVagaStr) {
		this.dataLiberacaoVagaStr = dataLiberacaoVagaStr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + altura;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime
				* result
				+ ((dataLiberacaoVaga == null) ? 0 : dataLiberacaoVaga
						.hashCode());
		result = prime
				* result
				+ ((dataLiberacaoVagaStr == null) ? 0 : dataLiberacaoVagaStr
						.hashCode());
		result = prime * result
				+ ((dataLocacaoVaga == null) ? 0 : dataLocacaoVaga.hashCode());
		result = prime
				* result
				+ ((dataLocacaoVagaStr == null) ? 0 : dataLocacaoVagaStr
						.hashCode());
		result = prime * result + id;
		result = prime * result + idEstacionamento;
		result = prime * result + largura;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((tipoVagaBean == null) ? 0 : tipoVagaBean.hashCode());
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
		VagaBean other = (VagaBean) obj;
		if (altura != other.altura)
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataLiberacaoVaga == null) {
			if (other.dataLiberacaoVaga != null)
				return false;
		} else if (!dataLiberacaoVaga.equals(other.dataLiberacaoVaga))
			return false;
		if (dataLiberacaoVagaStr == null) {
			if (other.dataLiberacaoVagaStr != null)
				return false;
		} else if (!dataLiberacaoVagaStr.equals(other.dataLiberacaoVagaStr))
			return false;
		if (dataLocacaoVaga == null) {
			if (other.dataLocacaoVaga != null)
				return false;
		} else if (!dataLocacaoVaga.equals(other.dataLocacaoVaga))
			return false;
		if (dataLocacaoVagaStr == null) {
			if (other.dataLocacaoVagaStr != null)
				return false;
		} else if (!dataLocacaoVagaStr.equals(other.dataLocacaoVagaStr))
			return false;
		if (id != other.id)
			return false;
		if (idEstacionamento != other.idEstacionamento)
			return false;
		if (largura != other.largura)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tipoVagaBean == null) {
			if (other.tipoVagaBean != null)
				return false;
		} else if (!tipoVagaBean.equals(other.tipoVagaBean))
			return false;
		return true;
	}

}