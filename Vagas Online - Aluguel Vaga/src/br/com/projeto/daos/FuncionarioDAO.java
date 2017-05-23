package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projeto.beans.FuncionarioBean;
import br.com.projeto.db.DB;

public class FuncionarioDAO {

	private static final String BUSCA_FUNCIONARIO_POR_ID 							=	"SELECT ID, CPF, EMAIL, LOGIN, NOME, ID_PERFIL, RG, SEXO FROM USUARIO WHERE ID = ?";
	private static final String	BUSCA_ID_DO_ESTACIONAMENTO_DO_FUNCIONARIO			=	"SELECT ID_ESTACIONAMENTO_FUNC FROM USUARIO WHERE ID = ?";
	
	public FuncionarioBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		FuncionarioBean funcionarioBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_FUNCIONARIO_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				funcionarioBean		=	new FuncionarioBean();
				funcionarioBean.setId(rs.getInt("ID"));
				funcionarioBean.setCpf(rs.getString("CPF"));
				funcionarioBean.setNome(rs.getString("NOME"));
				funcionarioBean.setLogin(rs.getString("LOGIN"));
				funcionarioBean.setEmail(rs.getString("EMAIL"));
				funcionarioBean.setSexo(rs.getString("SEXO"));
				funcionarioBean.setPerfil(rs.getInt("ID_PERFIL"));
				funcionarioBean.setRg(rs.getString("RG"));
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return funcionarioBean;	
	}

	public int buscaIdEstacionamentoDoFuncionario(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_ID_DO_ESTACIONAMENTO_DO_FUNCIONARIO);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("ID_ESTACIONAMENTO_FUNC");
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaIdEstacionamentoDoFuncionario. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}

		return 0;
	}

}