package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.TipoVagaBean;
import br.com.projeto.db.DB;

public class TipoVagaDAO {

	private static final String EXISTE_TIPO_VAGA			=	"SELECT 1 FROM TIPO_VAGA WHERE ID_ESTACIONAMENTO = ?";
	private static final String LISTAR_TODOS_TIPOS_VAGAS	=	"SELECT * FROM TIPO_VAGA WHERE ID_ESTACIONAMENTO = ?";
	private static final String BUSCA_TIPO_VAGA_POR_ID 		=	"SELECT * FROM TIPO_VAGA WHERE ID = ?";

	private static final String INSERIR_TIPO_VAGA 			=	"INSERT INTO TIPO_VAGA(NOME, PRECO, ID_ESTACIONAMENTO) VALUES(?, ?, ?)";
	private static final String ATUALIZAR_TIPO_VAGA	 		=	"UPDATE TIPO_VAGA SET NOME = ?, PRECO = ? WHERE ID = ?";
	private static final String EXCLUIR_TIPO_VAGA 			=	"DELETE FROM TIPO_VAGA WHERE ID = ?";
	private static final String EXISTE_VAGA_ASSOCIADA_AO_TIPO_DE_VAGA_QUE_SERA_EXCLUIDA = "SELECT * FROM VAGA WHERE ID_TIPO_VAGA = ?";


	public TipoVagaBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		TipoVagaBean tipoVagaBean 	=	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_TIPO_VAGA_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();

			if(rs.next()) {
				tipoVagaBean = new TipoVagaBean();
				tipoVagaBean.setId(rs.getInt("ID"));
				tipoVagaBean.setNome(rs.getString("NOME"));
				tipoVagaBean.setPreco(rs.getInt("PRECO"));
				tipoVagaBean.setIdEstacionamento(rs.getInt("ID_ESTACIONAMENTO"));
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return tipoVagaBean;	
	}


	public List<TipoVagaBean> listaTodos(int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<TipoVagaBean> listaTipoVagaBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS_TIPOS_VAGAS);
			pstmt.setInt(1, idEstacionamento);
			rs		=	pstmt.executeQuery();

			listaTipoVagaBean = new ArrayList<TipoVagaBean>();
			
			while(rs.next()) {
				TipoVagaBean tipoVagaBean = new TipoVagaBean();
				tipoVagaBean.setId(rs.getInt("ID"));
				tipoVagaBean.setNome(rs.getString("NOME"));
				tipoVagaBean.setPreco(rs.getInt("PRECO"));
				tipoVagaBean.setIdEstacionamento(rs.getInt("ID_ESTACIONAMENTO"));
				listaTipoVagaBean.add(tipoVagaBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaTipoVagaBean;	
	}


	public boolean existeTipoVagaJaCadastrada(int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXISTE_TIPO_VAGA);
			pstmt.setInt(1, idEstacionamento);
			rs		=	pstmt.executeQuery();

			if(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo existeTipoVagaJaCadastrada. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return false;	
	}


	public boolean excluir(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXCLUIR_TIPO_VAGA);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo excluir. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;			
	}


	public boolean inserir(TipoVagaBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR_TIPO_VAGA);
			pstmt.setString(1, obj.getNome());
			pstmt.setInt(2, obj.getPreco());
			pstmt.setInt(3, obj.getIdEstacionamento());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo inserir. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;
	}

	public boolean alterar(TipoVagaBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_TIPO_VAGA);
			pstmt.setString(1, obj.getNome());
			pstmt.setInt(2, obj.getPreco());
			pstmt.setInt(3, obj.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo alterar. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;
	}


	public boolean existeVagaAssociadaAoTipoDeVagaQueSeraExcluida(int idTipoVaga) {

		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXISTE_VAGA_ASSOCIADA_AO_TIPO_DE_VAGA_QUE_SERA_EXCLUIDA);
			pstmt.setInt(1, idTipoVaga);
			rs		=	pstmt.executeQuery();

			if(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo existeVagaAssociadaAoTipoDeVagaQueSeraExcluida. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return false;
	}

}