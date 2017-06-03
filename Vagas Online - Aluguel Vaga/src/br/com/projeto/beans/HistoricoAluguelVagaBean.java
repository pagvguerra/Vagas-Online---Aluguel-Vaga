package br.com.projeto.beans;

import java.io.Serializable;
import java.util.Date;

public class HistoricoAluguelVagaBean implements Serializable {

	private static final long serialVersionUID = 1212112650659349167L;

	private int id;
	private String tipoVaga;
	private String modelo;
	private String placa;
	private String codigoVaga;
	private Date horaEntrada;
	private Date horaSaida;
	private int valorCobrado;
	private String tipoPagamento;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoVaga() {
		return tipoVaga;
	}
	public void setTipoVaga(String tipoVaga) {
		this.tipoVaga = tipoVaga;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCodigoVaga() {
		return codigoVaga;
	}
	public void setCodigoVaga(String codigoVaga) {
		this.codigoVaga = codigoVaga;
	}
	public Date getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public Date getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}
	public int getValorCobrado() {
		return valorCobrado;
	}
	public void setValorCobrado(int valorCobrado) {
		this.valorCobrado = valorCobrado;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoVaga == null) ? 0 : codigoVaga.hashCode());
		result = prime * result
				+ ((horaEntrada == null) ? 0 : horaEntrada.hashCode());
		result = prime * result
				+ ((horaSaida == null) ? 0 : horaSaida.hashCode());
		result = prime * result + id;
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result
				+ ((tipoPagamento == null) ? 0 : tipoPagamento.hashCode());
		result = prime * result
				+ ((tipoVaga == null) ? 0 : tipoVaga.hashCode());
		result = prime * result + valorCobrado;
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
		HistoricoAluguelVagaBean other = (HistoricoAluguelVagaBean) obj;
		if (codigoVaga == null) {
			if (other.codigoVaga != null)
				return false;
		} else if (!codigoVaga.equals(other.codigoVaga))
			return false;
		if (horaEntrada == null) {
			if (other.horaEntrada != null)
				return false;
		} else if (!horaEntrada.equals(other.horaEntrada))
			return false;
		if (horaSaida == null) {
			if (other.horaSaida != null)
				return false;
		} else if (!horaSaida.equals(other.horaSaida))
			return false;
		if (id != other.id)
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (tipoPagamento == null) {
			if (other.tipoPagamento != null)
				return false;
		} else if (!tipoPagamento.equals(other.tipoPagamento))
			return false;
		if (tipoVaga == null) {
			if (other.tipoVaga != null)
				return false;
		} else if (!tipoVaga.equals(other.tipoVaga))
			return false;
		if (valorCobrado != other.valorCobrado)
			return false;
		return true;
	}
	
}