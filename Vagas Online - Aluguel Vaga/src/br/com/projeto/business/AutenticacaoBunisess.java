package br.com.projeto.business;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.beans.AutenticacaoBean;
import br.com.projeto.beans.EstacionamentoBean;
import br.com.projeto.beans.UsuarioBean;
import br.com.projeto.daos.AutenticacaoDAO;
import br.com.projeto.daos.EstacionamentoDAO;
import br.com.projeto.daos.FuncionarioDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class AutenticacaoBunisess {

	private static final String CAMPO_LOGIN = "LOGIN";
	private static final String CAMPO_SENHA = "SENHA";
	
	private static AutenticacaoBunisess instance = null;
	private String urlRetorno = "";

	public static AutenticacaoBunisess getInstance() {
		if ( instance == null ) {
			instance = new AutenticacaoBunisess();
		}
		return instance;
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try { 

			String acao = request.getParameter("acao");
				
			if(Util.isEmpty(acao) || acao.equalsIgnoreCase("LOGIN")) {
				login(request, response);
			} else if(acao.equalsIgnoreCase("LOGOUT")) {
				logout(request, response);
			} else if(acao.equalsIgnoreCase("ENTRADA")) {
				preencheRetorno(request, response, "", URLs.URL_LOGIN);	
			}
			
		} catch (Exception e) {
			System.out.println("Erro. Mensagem: " + e.getMessage());
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
		}	
		
		return urlRetorno;
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AutenticacaoBean autenticacaoBean =	null;

		try {
			
			String login = request.getParameter(CAMPO_LOGIN);
			String senha = request.getParameter(CAMPO_SENHA);
			
			autenticacaoBean = new AutenticacaoBean();
			autenticacaoBean.setLogin(login.trim());
			autenticacaoBean.setSenha(Util.criptografa(senha.trim()));
			
			UsuarioBean usuarioBean = new AutenticacaoDAO().existeUsuarioPorLoginESenhaInformados(autenticacaoBean);
			
			if(usuarioBean!=null) {
				
				//Primeiro valido se o estacionamento está ativo
				int idEstacionamento = new FuncionarioDAO().buscaIdEstacionamentoDoFuncionario(usuarioBean.getId());
				boolean estacionamentoAtivo = new EstacionamentoDAO().isEstacionamentoAtivo(idEstacionamento);

				if(estacionamentoAtivo) { 
					HttpSession session = request.getSession();
					session.setAttribute("usuario", usuarioBean);
					
					VagaBusiness.getInstance().listarTodos(idEstacionamento, request, response); 
					
					EstacionamentoBean estacionamentoBean = new EstacionamentoDAO().buscarPorId(idEstacionamento);
					session.setAttribute("estacionamentoBean", estacionamentoBean);
					
					preencheRetorno(request, response, null, URLs.URL_LISTA_ALUGUEL_VAGAS);
				} else {
					preencheRetorno(request, response, "Estacionamento ainda não ativo!<br>Necessário aguardar o cadastro completo do estacionamento", URLs.URL_ERRO_VALIDACAO);
				}
				
			} else {
				preencheRetorno(request, response, Mensagens.USUARIO_NAO_ENCONTRADO, URLs.URL_ERRO_VALIDACAO);
			}
			
		} catch (Exception e) {
			throw e;
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getSession().setAttribute("usuario", null);
		request.getSession().setAttribute("estacionamentoBean", null);
		request.getSession().invalidate();
		preencheRetorno(request, response, null, URLs.URL_LOGOUT);
	}
	
	public boolean validar(HttpServletRequest request, HttpServletResponse response, String campo, String msgCampoVazio, String msgTamanhoMinimo, int tam) throws IOException {
		if(Util.isEmpty(campo)) {
			preencheRetorno(request, response, msgCampoVazio, URLs.URL_ERRO_VALIDACAO);
			return false;
		}
		if(campo.length() >= tam) {
			preencheRetorno(request, response, msgTamanhoMinimo, URLs.URL_ERRO_VALIDACAO);
			return false;
		}
		return true;
	}

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}
		urlRetorno = url;
	}
	
}