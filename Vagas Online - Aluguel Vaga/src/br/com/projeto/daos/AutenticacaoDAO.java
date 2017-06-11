package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projeto.beans.AutenticacaoBean;
import br.com.projeto.beans.UsuarioBean;
import br.com.projeto.db.DB;
import br.com.projeto.enums.PerfilEnum;

public class AutenticacaoDAO {

	private static final String BUSCA_USUARIO_POR_LOGIN_E_SENHA_INFORMADOS	=	"SELECT ID, NOME, LOGIN, ID_PERFIL FROM USUARIO WHERE LOGIN = ? AND SENHA = ? AND STATUS = 'A' AND ID_PERFIL = ?";
	
	public UsuarioBean existeUsuarioPorLoginESenhaInformados(AutenticacaoBean entidade) 
	{		
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		
		UsuarioBean usuarioBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_USUARIO_POR_LOGIN_E_SENHA_INFORMADOS);
			pstmt.setString(1, entidade.getLogin());
			pstmt.setString(2, entidade.getSenha());
			pstmt.setInt(3, PerfilEnum.FUNCIONARIO.getCodigo());
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				usuarioBean		=	new UsuarioBean();
				usuarioBean.setId(rs.getInt("ID"));
				usuarioBean.setNome(rs.getString("NOME"));
				usuarioBean.setSenha(entidade.getSenha());
				usuarioBean.setPerfil(rs.getInt("ID_PERFIL"));
				usuarioBean.setLogin(rs.getString("LOGIN"));
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo existeUsuarioPorLoginESenhaInformados. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return usuarioBean;
	}
	
}