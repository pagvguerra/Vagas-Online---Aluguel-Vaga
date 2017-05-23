package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projeto.beans.EstacionamentoBean;
import br.com.projeto.beans.StatusBean;
import br.com.projeto.db.DB;
import br.com.projeto.enums.StatusEnum;

public class EstacionamentoDAO {

	private static final String BUSCA_ESTACIONAMENTO_POR_ID					=	"SELECT * FROM ESTACIONAMENTO WHERE ID = ?";
	private static final String ATUALIZAR_STATUS_ESTACIONAMENTO				=	"UPDATE ESTACIONAMENTO SET ID_STATUS = ? WHERE ID = ?";
	private static final String VALIDA_SE_ESTACIONAMENTO_ESTA_ATIVO			=	"SELECT 1 FROM ESTACIONAMENTO WHERE ID = ? AND ID_STATUS = ?";

	public EstacionamentoBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		EstacionamentoBean estacionamentoBean =	null;

		try {
		
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_ESTACIONAMENTO_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();

			if(rs.next()) {
				estacionamentoBean		=	new EstacionamentoBean();
				estacionamentoBean.setId(rs.getInt("ID"));
				estacionamentoBean.setNomeFantasia(rs.getString("NOME_FANTASIA"));
				estacionamentoBean.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
				estacionamentoBean.setCnpj(rs.getString("CNPJ"));
				estacionamentoBean.setInscricaoMunicipal(rs.getString("INSCRICAO_MUNICIPAL"));
				estacionamentoBean.setInscricaoEstadual(rs.getString("INSCRICAO_ESTADUAL"));
				
				StatusBean statusBean = new StatusBean();
				statusBean.setId(rs.getInt("ID_STATUS"));
				estacionamentoBean.setStatusBean(statusBean);
				estacionamentoBean.setEnderecoBean(EnderecoDAO.buscaEnderecoPorEstacionamento(rs.getInt("ID_ENDERECO")));
			}

		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return estacionamentoBean;	
	}
	
	public void atualizarStatusEstacionamento(int codigo, int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
	
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_STATUS_ESTACIONAMENTO);
			pstmt.setInt(1, codigo);
			pstmt.setInt(2, idEstacionamento);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo atualizarStatusEstacionamento. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, null);
		}
		
	}

	public boolean isEstacionamentoAtivo(int idEstacionamento) {
	
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
	
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(VALIDA_SE_ESTACIONAMENTO_ESTA_ATIVO);
			pstmt.setInt(1, idEstacionamento);
			pstmt.setInt(2, StatusEnum.ATIVO.getCodigo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo isEstacionamentoAtivo. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return false;
	}

}