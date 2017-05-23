package br.com.projeto.business;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.TipoVagaBean;
import br.com.projeto.beans.VagaBean;
import br.com.projeto.daos.VagaDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class VagaBusiness {

	private static VagaBusiness instance = null;
	private String urlRetorno = "";

	public static VagaBusiness getInstance() {
		if ( instance == null ) {
			instance = new VagaBusiness();
		}
		return instance;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try { 
			
			String acao = request.getParameter("acao");
			
			if(Util.isEmpty(acao)) {
				System.out.println("Ação nao definida");
				preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				geraListaEEncaminhaParaTelaDeListagem(request, response);
			} else if(acao.equalsIgnoreCase("ALUGAR_VAGA")) {
				alugarVaga(request, response);
				geraListaEEncaminhaParaTelaDeListagem(request, response);
			} else if(acao.equalsIgnoreCase("PREPARAR_LIBERAR_VAGA")) {
				prepararLiberarVaga(request, response);
				preencheRetorno(request, response, null, "/paginas/estacionamento/vagas/liberarVaga.jsp");
			} else if(acao.equalsIgnoreCase("LIBERAR_VAGA")) {
				liberarVaga(request, response);
				geraListaEEncaminhaParaTelaDeListagem(request, response);
			}
		
		} catch (Exception e) {
			System.out.println("Erro. Mensagem: " + e.getMessage());
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
		}	
		
		return urlRetorno;
	}

	private void geraListaEEncaminhaParaTelaDeListagem(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		listarTodos(request, response);
		preencheRetorno(request, response, null, "/paginas/estacionamento/vagas/listarVagasParaAluguel.jsp");
	}

	private void alugarVaga(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			int idVaga = Integer.parseInt(request.getParameter("id"));
			//VagaBean vagaBean = new VagaDAO().buscarPorId(idVaga);
		
			new VagaDAO().alugarVaga(idVaga);

			//registrar historico de aluguel da vaga nesse ponto, informando vaga alugada e horario de entrada, colocar o status para vaga Indisponivel
			//new HistoricoVagaDAO().inserirHistoricoVaga(vagaBean);
			request.setAttribute("msgSucessoAlugar", "Vaga alugada com sucesso");
		
		} catch (Exception e) {
			throw e;
		}		
	}

	private void prepararLiberarVaga(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);

			int idVaga = Integer.parseInt(request.getParameter("id"));
			VagaBean vagaBean = new VagaDAO().buscarPorId(idVaga);
			request.setAttribute("vagaBean", vagaBean);
			
			int horasUtilizadasLocacao = obterQuantidadeHorasUtilizadasLocacao(vagaBean);
			request.setAttribute("horasUtilizadasLocacao", horasUtilizadasLocacao);
			
			int precoAPagar = obterPrecoAPagar(vagaBean, horasUtilizadasLocacao);
			request.setAttribute("precoAPagar", precoAPagar);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private int obterPrecoAPagar(VagaBean vagaBean, int horasFechamento) {
		int precoAPagar;
		if(horasFechamento == 0) {
			precoAPagar = vagaBean.getTipoVagaBean().getPreco();
		} else {
			precoAPagar = horasFechamento * vagaBean.getTipoVagaBean().getPreco();
		}
		return precoAPagar;
	}

	private int obterQuantidadeHorasUtilizadasLocacao(VagaBean vagaBean) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		long horaAtual = calendar.getTimeInMillis();
		
		final Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(vagaBean.getDataLocacaoVaga());
		long horaLocacao = calendar2.getTimeInMillis();
		
		long horasFechamento = (horaAtual - horaLocacao)/3600000;
		int horasFechamentoInt = Math.round(horasFechamento);
		
		return horasFechamentoInt;
	}

	private void liberarVaga(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);

			int idVaga = Integer.parseInt(request.getParameter("id"));
			VagaBean vagaBean = new VagaDAO().buscarPorId(idVaga);
			new VagaDAO().liberarVaga(vagaBean);
			
			//registrar historico de aluguel da vaga nesse ponto, informando vaga fechada, horario de entrada, horario de saida e valor a pagar, colocar o status para vaga fechada (status F) com data de entrada e saida
			//new HistoricoVagaDAO().inserirHistoricoVaga(vagaBean);
			
		} catch (Exception e) {
			throw e;
		}
	}

	private void listarTodos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			List<VagaBean> listaVagas = new VagaDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaVagas", listaVagas);
			request.setAttribute("idEstacionamento", idEstacionamento);
		} catch (Exception e) {
			throw e;
		}		
	}
	
	public void listarTodos(int idEstacionamento, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			List<VagaBean> listaVagas = new VagaDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaVagas", listaVagas);
			request.setAttribute("idEstacionamento", idEstacionamento);
		} catch (Exception e) {
			throw e;
		}		
	}
	
	public VagaBean retornaDados(HttpServletRequest request, boolean pegaId) {
		int id	=	0;
		
		if(pegaId)
			id			=	Integer.parseInt(request.getParameter("id"));
		
		String codigo	=	request.getParameter("codigo");
		int tipoVaga	=	Integer.parseInt(request.getParameter("tipoVaga"));
		int largura		=	Integer.parseInt(request.getParameter("largura"));
		int altura		=	Integer.parseInt(request.getParameter("altura"));
		
		TipoVagaBean tipoVagaBean = new TipoVagaBean();
		tipoVagaBean.setId(tipoVaga);
	
		VagaBean vagaBean = new VagaBean();
		vagaBean.setId(id);
		vagaBean.setCodigo(codigo);
		vagaBean.setTipoVagaBean(tipoVagaBean);
		vagaBean.setLargura(largura);
		vagaBean.setAltura(altura);
		
		return vagaBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
	
}