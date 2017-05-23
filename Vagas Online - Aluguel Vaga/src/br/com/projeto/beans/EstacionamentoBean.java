package br.com.projeto.beans;

import java.io.Serializable;

import br.com.projeto.daos.EstacionamentoDAO;
import br.com.projeto.enums.StatusEnum;

public class EstacionamentoBean implements Serializable {
	
	private static final long serialVersionUID = 6750485015161261228L;

	private int id;
	private String razaoSocial;
	private String nomeFantasia;
	private String cnpj;
	private String inscricaoMunicipal;
	private String inscricaoEstadual;
	private StatusBean statusBean;
	private EnderecoBean enderecoBean;
	
	private int quantidadeFuncionarios;
	private int quantidadeVagas;
	
	public int getQuantidadeFuncionarios() {
		return quantidadeFuncionarios;
	}
	public void setQuantidadeFuncionarios(int quantidadeFuncionarios) {
		this.quantidadeFuncionarios = quantidadeFuncionarios;
	}
	public int getQuantidadeVagas() {
		return quantidadeVagas;
	}
	public void setQuantidadeVagas(int quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}
	public EnderecoBean getEnderecoBean() {
		return enderecoBean;
	}
	public void setEnderecoBean(EnderecoBean enderecoBean) {
		this.enderecoBean = enderecoBean;
	}
	public StatusBean getStatusBean() {
		return statusBean;
	}
	public void setStatusBean(StatusBean statusBean) {
		this.statusBean = statusBean;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public static int obterStatusEstacionamentoEncaminharAoStatusSeguinte(int idEstacionamento) {
		
		int status = 0;
		EstacionamentoBean estacionamentoBean = new EstacionamentoDAO().buscarPorId(idEstacionamento);
		
		if(StatusEnum.PENDENTE.getCodigo() == estacionamentoBean.getStatusBean().getId()) {
			status = StatusEnum.EM_PROGRESSO.getCodigo();
		} else if ( (StatusEnum.EM_PROGRESSO.getCodigo() == estacionamentoBean.getStatusBean().getId()) || (StatusEnum.ATIVO.getCodigo() == estacionamentoBean.getStatusBean().getId())) {
			status = StatusEnum.ATIVO.getCodigo();
		}
		
		return status;
	}
	
	public static void alteraStatusEstacionamentoParaOStatusSeguinte (int idEstacionamento) {
		int status = EstacionamentoBean.obterStatusEstacionamentoEncaminharAoStatusSeguinte(idEstacionamento);
		new EstacionamentoDAO().atualizarStatusEstacionamento(status, idEstacionamento);
	}

	public static int obterStatusEstacionamentoEncaminharAoStatusAnterior(int idEstacionamento) {
	
		int status = 0;
		EstacionamentoBean estacionamentoBean = new EstacionamentoDAO().buscarPorId(idEstacionamento);
		
		if(StatusEnum.ATIVO.getCodigo() == estacionamentoBean.getStatusBean().getId()) {
			status = StatusEnum.EM_PROGRESSO.getCodigo();
		} else if( (StatusEnum.EM_PROGRESSO.getCodigo() == estacionamentoBean.getStatusBean().getId()) || StatusEnum.PENDENTE.getCodigo() == estacionamentoBean.getStatusBean().getId()) {
			status = StatusEnum.PENDENTE.getCodigo();
		} 
				
		return status;
	}
	
	public static void alteraStatusEstacionamentoParaOStatusAnterior (int idEstacionamento) {
		int status = EstacionamentoBean.obterStatusEstacionamentoEncaminharAoStatusAnterior(idEstacionamento);
		new EstacionamentoDAO().atualizarStatusEstacionamento(status, idEstacionamento);
	}
	
}