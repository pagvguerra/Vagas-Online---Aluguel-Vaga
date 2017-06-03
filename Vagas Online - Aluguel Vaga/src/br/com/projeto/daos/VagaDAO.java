package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.TipoVagaBean;
import br.com.projeto.beans.VagaBean;
import br.com.projeto.db.DB;

public class VagaDAO {

	private static final String BUSCA_VAGA_POR_ID 						=	"SELECT ID, CODIGO, ID_ESTACIONAMENTO, ID_TIPO_VAGA, LARGURA, ALTURA, STATUS, DATA_LOCACAO_VAGA FROM VAGA WHERE ID = ?";
	private static final String LISTAR_TODOS_VAGAS_POR_ESTACIONAMENTO 	=	"SELECT ID, CODIGO, ID_ESTACIONAMENTO, ID_TIPO_VAGA, LARGURA, ALTURA, STATUS, DATA_LOCACAO_VAGA FROM VAGA WHERE ID_ESTACIONAMENTO = ?";
	private static final String ALUGAR_VAGA 							=	"UPDATE VAGA SET STATUS = ?, DATA_LOCACAO_VAGA = CURRENT_TIMESTAMP WHERE ID = ?";
	private static final String LIBERAR_VAGA 							=	"UPDATE VAGA SET STATUS = ?, DATA_LOCACAO_VAGA = NULL WHERE ID = ?";

	private static final DateFormat dateFormat							=	new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	
	public VagaBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		VagaBean vagaBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_VAGA_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();

			if(rs.next()) {
				
				TipoVagaBean tipoVagaBean = new TipoVagaDAO().buscarPorId((rs.getInt("ID_TIPO_VAGA")));
				
				vagaBean		=	new VagaBean();
				vagaBean.setId(rs.getInt("ID"));
				vagaBean.setIdEstacionamento(rs.getInt("ID_ESTACIONAMENTO"));
				vagaBean.setTipoVagaBean(tipoVagaBean);
				vagaBean.setCodigo(rs.getString("CODIGO"));
				vagaBean.setLargura(rs.getInt("LARGURA"));
				vagaBean.setAltura(rs.getInt("ALTURA"));
				vagaBean.setStatus(rs.getString("STATUS"));
				
				if(rs.getTimestamp("DATA_LOCACAO_VAGA") != null) {
					vagaBean.setDataLocacaoVaga(rs.getTimestamp("DATA_LOCACAO_VAGA"));
					vagaBean.setDataLocacaoVagaStr(dateFormat.format(rs.getTimestamp("DATA_LOCACAO_VAGA")));
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return vagaBean;	
	}


	public List<VagaBean> listaTodos(int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<VagaBean> listaVagaBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS_VAGAS_POR_ESTACIONAMENTO);
			pstmt.setInt(1, idEstacionamento);
			rs		=	pstmt.executeQuery();

			listaVagaBean = new ArrayList<VagaBean>();
			
			while(rs.next()) {
				
				TipoVagaBean tipoVagaBean = new TipoVagaDAO().buscarPorId((rs.getInt("ID_TIPO_VAGA")));
				
				VagaBean vagaBean		=	new VagaBean();
				vagaBean.setId(rs.getInt("ID"));
				vagaBean.setIdEstacionamento(rs.getInt("ID_ESTACIONAMENTO"));
				vagaBean.setTipoVagaBean(tipoVagaBean);
				vagaBean.setCodigo(rs.getString("CODIGO"));
				vagaBean.setLargura(rs.getInt("LARGURA"));
				vagaBean.setAltura(rs.getInt("ALTURA"));
				vagaBean.setStatus(rs.getString("STATUS"));

				if(rs.getTimestamp("DATA_LOCACAO_VAGA") != null) {
					vagaBean.setDataLocacaoVaga(rs.getTimestamp("DATA_LOCACAO_VAGA"));
					vagaBean.setDataLocacaoVagaStr(dateFormat.format(rs.getTimestamp("DATA_LOCACAO_VAGA")));
				}
				
				listaVagaBean.add(vagaBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaVagaBean;	
	}


	public void alugarVaga(int idVaga) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ALUGAR_VAGA);
			pstmt.setString(1, "I");
			pstmt.setInt(2, idVaga);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo alugarVaga. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, null);
		}
	}


	public void liberarVaga(VagaBean vagaBean) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LIBERAR_VAGA);
			pstmt.setString(1, "D");
			pstmt.setInt(2, vagaBean.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo liberarVaga. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, null);
		}
		
	}
	
}