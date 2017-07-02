package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import br.com.projeto.beans.HistoricoAluguelVagaBean;
import br.com.projeto.beans.VagaBean;
import br.com.projeto.db.DB;

import com.mysql.jdbc.Statement;

public class HistoricoAluguelVagaDAO {

	private static final String INSERIR	=	"INSERT INTO HISTORICO_ALUGUEL(TIPO_VAGA, MODELO, PLACA, CODIGO_VAGA, HORA_ENTRADA, ID_ESTACIONAMENTO) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String BUSCA_DADOS_PARA_ENCONTRAR_HISTORICO_ALUGUEL_VAGA = "SELECT * FROM HISTORICO_ALUGUEL WHERE TIPO_VAGA = ? AND CODIGO_VAGA = ? AND HORA_ENTRADA = ?";
	private static final String ATUALIZAR_HISTORICO_ALUGUEL_VAGA = "UPDATE HISTORICO_ALUGUEL SET HORA_SAIDA = CURRENT_TIMESTAMP, VALOR_COBRADO = ?, TIPO_PAGAMENTO = ? WHERE ID = ?";
	
	public void inserirHistoricoAluguelVaga(VagaBean vagaBean, String modelo, String placa, int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs 				= 	null;
		
		try {
			
			Timestamp tmp			=	new Timestamp(vagaBean.getDataLocacaoVaga().getTime());

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, vagaBean.getTipoVagaBean().getNome());
			pstmt.setString(2, modelo);
			pstmt.setString(3, placa);
			pstmt.setString(4, vagaBean.getCodigo());
			pstmt.setTimestamp(5, tmp);
			pstmt.setInt(6, idEstacionamento);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Erro no metodo inserir. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}

	}

	public HistoricoAluguelVagaBean buscaDadosParaEncontrarHistoricoAluguelVaga(VagaBean vagaBean) {

		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
	
		HistoricoAluguelVagaBean historicoAluguelVagaBean =	null;

		try {
		
			Timestamp tmp			=	new Timestamp(vagaBean.getDataLocacaoVaga().getTime());
			
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_DADOS_PARA_ENCONTRAR_HISTORICO_ALUGUEL_VAGA);
			pstmt.setString(1, vagaBean.getTipoVagaBean().getNome());
			pstmt.setString(2, vagaBean.getCodigo());
			pstmt.setTimestamp(3, tmp);
			rs		=	pstmt.executeQuery();

			if(rs.next()) { 
				historicoAluguelVagaBean = new HistoricoAluguelVagaBean();
				historicoAluguelVagaBean.setId(rs.getInt("ID"));
				historicoAluguelVagaBean.setTipoVaga(rs.getString("TIPO_VAGA"));
				historicoAluguelVagaBean.setModelo(rs.getString("MODELO"));
				historicoAluguelVagaBean.setPlaca(rs.getString("PLACA"));
				historicoAluguelVagaBean.setCodigoVaga(rs.getString("CODIGO_VAGA"));
				historicoAluguelVagaBean.setHoraEntrada(rs.getDate("HORA_ENTRADA"));
			}

		} catch (Exception e) {
			System.out.println("Erro no metodo buscaDadosParaEncontrarHistoricoAluguelVaga. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return historicoAluguelVagaBean;	

	}

	public void atualizarHistoricoAluguelVaga(HistoricoAluguelVagaBean historicoAluguelVagaBean) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_HISTORICO_ALUGUEL_VAGA);
			pstmt.setInt(1, historicoAluguelVagaBean.getValorCobrado());
			pstmt.setString(2, historicoAluguelVagaBean.getTipoPagamento());					
			pstmt.setInt(3, historicoAluguelVagaBean.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo atualizarHistoricoAluguelVaga. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
	}

}