package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projeto.beans.TipoPagamentoBean;
import br.com.projeto.db.DB;

public class TipoPagamentoDAO {

	private static final String BUSCA_TIPO_PAGAMENTO_POR_ID = "SELECT NOME FROM TIPO_PAGAMENTO WHERE ID = ?";
	
	public TipoPagamentoBean buscarTipoPagamentoPorId(int idTipoPagamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
	
		TipoPagamentoBean tipoPagamentoBean =	null;

		try {
		
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_TIPO_PAGAMENTO_POR_ID);
			pstmt.setInt(1, idTipoPagamento);
			rs		=	pstmt.executeQuery();

			if(rs.next()) { 
				tipoPagamentoBean = new TipoPagamentoBean();
				tipoPagamentoBean.setId(idTipoPagamento);
				tipoPagamentoBean.setNome(rs.getString("NOME"));
			}

		} catch (Exception e) {
			System.out.println("Erro no metodo buscarTipoPagamentoPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return tipoPagamentoBean;	

	}

}
