package br.com.projeto.beans;

import java.io.Serializable;

public class EstacionamentoTipoPagamentoBean implements Serializable {

	private static final long serialVersionUID = 377473801616006787L;

	private EstacionamentoBean estacionamentoBean;
	private TipoPagamentoBean tipoPagamentoBean;
	
	public EstacionamentoBean getEstacionamentoBean() {
		return estacionamentoBean;
	}
	public void setEstacionamentoBean(EstacionamentoBean estacionamentoBean) {
		this.estacionamentoBean = estacionamentoBean;
	}
	public TipoPagamentoBean getTipoPagamentoBean() {
		return tipoPagamentoBean;
	}
	public void setTipoPagamentoBean(TipoPagamentoBean tipoPagamentoBean) {
		this.tipoPagamentoBean = tipoPagamentoBean;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((estacionamentoBean == null) ? 0 : estacionamentoBean
						.hashCode());
		result = prime
				* result
				+ ((tipoPagamentoBean == null) ? 0 : tipoPagamentoBean
						.hashCode());
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
		EstacionamentoTipoPagamentoBean other = (EstacionamentoTipoPagamentoBean) obj;
		if (estacionamentoBean == null) {
			if (other.estacionamentoBean != null)
				return false;
		} else if (!estacionamentoBean.equals(other.estacionamentoBean))
			return false;
		if (tipoPagamentoBean == null) {
			if (other.tipoPagamentoBean != null)
				return false;
		} else if (!tipoPagamentoBean.equals(other.tipoPagamentoBean))
			return false;
		return true;
	}
	
}