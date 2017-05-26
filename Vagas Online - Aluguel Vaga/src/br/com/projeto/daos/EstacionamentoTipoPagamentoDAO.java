package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.EstacionamentoBean;
import br.com.projeto.beans.EstacionamentoTipoPagamentoBean;
import br.com.projeto.beans.TipoPagamentoBean;
import br.com.projeto.db.DB;

public class EstacionamentoTipoPagamentoDAO {

	private static final String LISTAR_TIPOS_PAGAMENTO_POR_ESTACIONAMENTO	=	"SELECT e.id as id_estacionamento, e.nome_fantasia as nome_fantasia, tp.id as id_tipo_pagamento, tp.nome as nome_tipo_pagamento FROM estacionamento e, estacionamento_tp_pagamento etp, tipo_pagamento tp WHERE etp.id_estacionamento = e.id AND etp.id_tipo_pagamento = tp.id AND e.id = ?;";
	
	public List<EstacionamentoTipoPagamentoBean> listaTiposPagamentosPorEstacionamento(int idEstacionamento) {
		Connection conn																=	null;
		PreparedStatement pstmt														=	null;
		ResultSet rs																=	null;
		List<EstacionamentoTipoPagamentoBean> listaEstacionamentoTipoPagamentoBean 	=	null;
		
		try {
			
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TIPOS_PAGAMENTO_POR_ESTACIONAMENTO);
			pstmt.setInt(1, idEstacionamento);
			rs		=	pstmt.executeQuery();

			listaEstacionamentoTipoPagamentoBean									=	new ArrayList<EstacionamentoTipoPagamentoBean>();
			
			while(rs.next()) {
				
				EstacionamentoBean estacionamentoBean 								=	new EstacionamentoBean();
				estacionamentoBean.setId(rs.getInt("ID_ESTACIONAMENTO"));
				estacionamentoBean.setNomeFantasia(rs.getString("NOME_FANTASIA"));
				
				TipoPagamentoBean tipoPagamentoBean									=	new TipoPagamentoBean();
				tipoPagamentoBean.setId(rs.getInt("ID_TIPO_PAGAMENTO"));
				tipoPagamentoBean.setNome(rs.getString("NOME_TIPO_PAGAMENTO"));
				
				EstacionamentoTipoPagamentoBean estacionamentoTipoPagamentoBean 	=	new EstacionamentoTipoPagamentoBean();
				estacionamentoTipoPagamentoBean.setEstacionamentoBean(estacionamentoBean);
				estacionamentoTipoPagamentoBean.setTipoPagamentoBean(tipoPagamentoBean);
				
				listaEstacionamentoTipoPagamentoBean.add(estacionamentoTipoPagamentoBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTiposPagamentosPorEstacionamento. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaEstacionamentoTipoPagamentoBean;	
	}
	
}