package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.UsuarioBean;
import br.com.projeto.db.DB;
import br.com.projeto.interfaces.GenericDAO;

public class UsuarioDAO implements GenericDAO<UsuarioBean> {

	private static final String INSERIR_USUARIO 		=	"INSERT INTO USUARIO(CPF, EMAIL, LOGIN, NOME, PERFIL, RG, SENHA, SEXO) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ATUALIZAR_USUARIO	 	=	"UPDATE USUARIO SET CPF = ?, EMAIL = ?, LOGIN = ?, NOME = ?, PERFIL = ?, RG = ?, SEXO = ?, SENHA = ? WHERE ID = ?";
	private static final String EXCLUIR_USUARIO 		=	"DELETE FROM USUARIO WHERE ID = ?";
	private static final String BUSCA_USUARIO_POR_ID 	=	"SELECT ID, CPF, EMAIL, LOGIN, NOME, PERFIL, RG, SENHA, SEXO FROM USUARIO WHERE ID = ?";
	private static final String LISTAR_TODOS_USUARIOS 	=	"SELECT ID, CPF, EMAIL, LOGIN, NOME, PERFIL, RG, SENHA, SEXO FROM USUARIO";
	
	@Override
	public boolean inserir(UsuarioBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR_USUARIO);
			pstmt.setString(1, obj.getCpf());
			pstmt.setString(2, obj.getEmail());
			pstmt.setString(3, obj.getLogin());					
			pstmt.setString(4, obj.getNome());
			pstmt.setInt(5, obj.getPerfil());
			pstmt.setString(6, obj.getRg());
			pstmt.setString(7, obj.getSenha());
			pstmt.setString(8, obj.getSexo());
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

	@Override
	public boolean alterar(UsuarioBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_USUARIO);
			pstmt.setString(1, obj.getCpf());
			pstmt.setString(2, obj.getEmail());
			pstmt.setString(3, obj.getLogin());					
			pstmt.setString(4, obj.getNome());
			pstmt.setInt(5, obj.getPerfil());
			pstmt.setString(6, obj.getRg());
			pstmt.setString(7, obj.getSexo());
			pstmt.setString(8, obj.getSenha());
			pstmt.setInt(9, obj.getId());
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

	@Override
	public boolean excluir(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXCLUIR_USUARIO);
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

	@Override
	public UsuarioBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		UsuarioBean usuarioBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_USUARIO_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				usuarioBean		=	new UsuarioBean();
				usuarioBean.setId(rs.getInt("ID"));
				usuarioBean.setCpf(rs.getString("CPF"));
				usuarioBean.setNome(rs.getString("NOME"));
				usuarioBean.setLogin(rs.getString("LOGIN"));
				usuarioBean.setSenha(rs.getString("SENHA"));
				usuarioBean.setEmail(rs.getString("EMAIL"));
				usuarioBean.setSexo(rs.getString("SEXO"));
				usuarioBean.setPerfil(rs.getInt("PERFIL"));
				usuarioBean.setRg(rs.getString("RG"));
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return usuarioBean;	
	}

	@Override
	public List<UsuarioBean> listaTodos() {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<UsuarioBean> listaUsuarioBean =	null;
		
		try {
			
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS_USUARIOS);
			rs		=	pstmt.executeQuery();
			listaUsuarioBean 		=	new ArrayList<UsuarioBean>();
			
			while(rs.next()) {
				UsuarioBean usuarioBean		=	new UsuarioBean();
				usuarioBean.setId(rs.getInt("ID"));
				usuarioBean.setCpf(rs.getString("CPF"));
				usuarioBean.setNome(rs.getString("NOME"));
				usuarioBean.setLogin(rs.getString("LOGIN"));
				usuarioBean.setSenha(rs.getString("SENHA"));
				usuarioBean.setEmail(rs.getString("EMAIL"));
				usuarioBean.setSexo(rs.getString("SEXO"));
				usuarioBean.setPerfil(rs.getInt("PERFIL"));
				usuarioBean.setRg(rs.getString("RG"));
				listaUsuarioBean.add(usuarioBean);
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return listaUsuarioBean;
	}

}