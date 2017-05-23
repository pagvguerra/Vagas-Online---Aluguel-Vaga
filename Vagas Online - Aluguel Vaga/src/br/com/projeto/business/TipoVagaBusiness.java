package br.com.projeto.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.TipoVagaBean;
import br.com.projeto.daos.TipoVagaDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class TipoVagaBusiness {

	private static TipoVagaBusiness instance = null;
	private String urlRetorno = "";

	public static TipoVagaBusiness getInstance() {
		if ( instance == null ) {
			instance = new TipoVagaBusiness();
		}
		return instance;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try { 
			
			String acao = request.getParameter("acao");
			if(Util.isEmpty(acao)) {
				System.out.println("Ação nao definida");
				preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
			} else if(acao.equalsIgnoreCase("INSERIR")) {
				inserir(request, response);
			} else if(acao.equalsIgnoreCase("ALTERAR")) {
				alterar(request, response);
			} else if(acao.equalsIgnoreCase("EXCLUIR")) {
				excluir(request, response);
			} else if(acao.equalsIgnoreCase("DETALHAR")) {
				detalhar(request, response);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				listarTodos(request, response);
				preencheRetorno(request, response, null, "/paginas/estacionamento/vagas/tipo_vagas/listarTipoVagas.jsp");
			} else if(acao.equalsIgnoreCase("PREPARAR_INSERIR")) {	
				prepararInserir(request, response);
			}
		
		} catch (Exception e) {
			System.out.println("Erro. Mensagem: " + e.getMessage());
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
		}	

		return urlRetorno;
	}
	
	private void prepararInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
		request.setAttribute("idEstacionamento", idEstacionamento);
		request.setAttribute("msg", null);
		preencheRetorno(request, response, null, "/paginas/estacionamento/vagas/tipo_vagas/cadastrarTipoVaga.jsp");
	}
	
	private void detalhar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			TipoVagaBean tipoVagaBean = new TipoVagaDAO().buscarPorId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("tipoVagaBean", tipoVagaBean);
			preencheRetorno(request, response, null, "/paginas/estacionamento/vagas/tipo_vagas/detalheTipoVaga.jsp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{

			//Verificar se tem uma vaga associada ao tipo de vaga que será excluida, se sim, então nao excluir
			int idTipoVaga = Integer.parseInt(request.getParameter("id"));
			
			boolean existeVagaAssociadaAoTipoDeVagaQueSeraExcluida = new TipoVagaDAO().existeVagaAssociadaAoTipoDeVagaQueSeraExcluida(idTipoVaga);
			
			if(existeVagaAssociadaAoTipoDeVagaQueSeraExcluida) {
				String mensagemImpossibilidadeExclusaoDeTipoDeVaga  = "Existe(m) vaga(s) associada(s) a este tipo de vaga. <br>Antes de excluir este tipo de vaga, exclua a(s) vaga(s) associada(s).";
				request.setAttribute("mensagemImpossibilidadeExclusaoDeTipoDeVaga", mensagemImpossibilidadeExclusaoDeTipoDeVaga);
			} else {
				new TipoVagaDAO().excluir(idTipoVaga);
			}
		
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			
			List<TipoVagaBean> listaTipoVagas = new TipoVagaDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaTipoVagas", listaTipoVagas);
			
			preencheRetorno(request, response, null, "/paginas/estacionamento/vagas/tipo_vagas/listarTipoVagas.jsp");
			
		} catch (Exception e) {
			throw e;
		}
	}

	private void alterar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			TipoVagaBean vagaBean = retornaDados(request, true);
			new TipoVagaDAO().alterar(vagaBean);
			preencheRetorno(request, response, "Tipo de Vaga atualizada com sucesso", "/paginas/estacionamento/vagas/tipo_vagas/sucessoAlterarTipoVaga.jsp");
		} catch (Exception e) {
			throw e;
		}		
	}

	private void listarTodos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			List<TipoVagaBean> listaTipoVagas = new TipoVagaDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaTipoVagas", listaTipoVagas);
			request.setAttribute("idEstacionamento", idEstacionamento);
		} catch (Exception e) {
			throw e;
		}		
	}
	
	private void inserir(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			TipoVagaBean tipoVagaBean = retornaDados(request, false);
			boolean cadastrou = new TipoVagaDAO().inserir(tipoVagaBean);
			
			if(!cadastrou) {
				throw new Exception("Erro ao inserir o tipo da vaga");
			}
			
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);

			String msgAnterior = (request.getParameter("msg")!=null)? (String)request.getParameter("msg") : null;
			request.setAttribute("msgAnterior", msgAnterior);
			
			preencheRetorno(request, response, "Tipo da Vaga inserida com sucesso", "/paginas/estacionamento/vagas/tipo_vagas/sucessoCadastrarTipoVaga.jsp");
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public TipoVagaBean retornaDados(HttpServletRequest request, boolean pegaId) {
		int id					=	0;
		
		if(pegaId)
			id					=	Integer.parseInt(request.getParameter("id"));
		
		String nome				=	request.getParameter("nome");
		int preco				=	Integer.parseInt(request.getParameter("preco"));
		int idEstacionamento	=	Integer.parseInt(request.getParameter("idEstacionamento"));

		TipoVagaBean tipoVagaBean = new TipoVagaBean();
		tipoVagaBean.setId(id);
		tipoVagaBean.setNome(nome);
		tipoVagaBean.setPreco(preco);
		tipoVagaBean.setIdEstacionamento(idEstacionamento);
		
		return tipoVagaBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
	
}