package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projeto.beans.BairroBean;
import br.com.projeto.beans.CidadeBean;
import br.com.projeto.beans.EnderecoBean;
import br.com.projeto.beans.EstadoBean;
import br.com.projeto.beans.PaisBean;
import br.com.projeto.beans.TipoLogradouroBean;
import br.com.projeto.db.DB;

import com.mysql.jdbc.Statement;

public class EnderecoDAO {

	private static final String BUSCA_POR_ID 				=	"SELECT E.ID AS ID_ENDERECO, E.NOME_LOGRADOURO AS NOME_LOGRADOURO, E.NUMERO AS NUMERO_LOGRADOURO, E.CEP AS CEP_LOGRADOURO, T.ID AS ID_TIPOLOGRADOURO, T.NOME AS NOME_TIPOLOGRADOURO, P.ID AS ID_PAIS, P.NOME AS NOME_PAIS, EST.ID AS ID_ESTADO, EST.NOME AS NOME_ESTADO, C.ID AS ID_CIDADE, C.NOME AS NOME_CIDADE, B.ID AS ID_BAIRRO, B.NOME AS NOME_BAIRRO FROM ENDERECO E, TIPO_LOGRADOURO T, PAIS P, ESTADO EST, CIDADE C, BAIRRO B WHERE E.ID = ? AND E.ID_TIPO_LOGRADOURO = T.ID AND E.ID_PAIS = P.ID AND E.ID_ESTADO = EST.ID AND E.ID_CIDADE = C.ID AND E.ID_BAIRRO = B.ID";
	private static final String INSERIR_ENDERECO			=	"INSERT INTO ENDERECO(NOME_LOGRADOURO, NUMERO, CEP, ID_TIPO_LOGRADOURO, ID_BAIRRO, ID_CIDADE, ID_ESTADO, ID_PAIS) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ALTERAR_ENDERECO			=	"UPDATE ENDERECO SET NOME_LOGRADOURO = ?, NUMERO = ?, CEP = ?, ID_TIPO_LOGRADOURO = ?, ID_BAIRRO = ?, ID_CIDADE = ?, ID_ESTADO = ?, ID_PAIS = ? WHERE ID = ?";

	
	public static EnderecoBean buscaEnderecoPorEstacionamento(int idEndereco) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		EnderecoBean enderecoBean	=	new EnderecoBean();

		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_POR_ID);
			pstmt.setInt(1, idEndereco);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
			
				//TIPO LOGRADOURO
				TipoLogradouroBean tipoLogradouroBean = new TipoLogradouroBean();
				tipoLogradouroBean.setId(rs.getInt("ID_TIPOLOGRADOURO"));
				tipoLogradouroBean.setNome(rs.getString("NOME_TIPOLOGRADOURO"));
				
				//BAIRRO
				BairroBean bairroBean = new BairroBean();
				bairroBean.setId(rs.getInt("ID_BAIRRO"));
				bairroBean.setNome(rs.getString("NOME_BAIRRO"));
				
				//CIDADE
				CidadeBean cidadeBean = new CidadeBean();
				cidadeBean.setId(rs.getInt("ID_CIDADE"));
				cidadeBean.setNome(rs.getString("NOME_CIDADE"));
				
				//ESTADO
				EstadoBean estadoBean = new EstadoBean();
				estadoBean.setId(rs.getInt("ID_ESTADO"));
				estadoBean.setNome(rs.getString("NOME_ESTADO"));
				
				//PAIS
				PaisBean paisBean = new PaisBean();
				paisBean.setId(rs.getInt("ID_PAIS"));
				paisBean.setNome(rs.getString("NOME_PAIS"));

				//ENDERECO
				enderecoBean.setPaisBean(paisBean);
				enderecoBean.setId(rs.getInt("ID_ENDERECO"));
				enderecoBean.setNomeLogradouro(rs.getString("NOME_LOGRADOURO"));
				enderecoBean.setNumero(rs.getInt("NUMERO_LOGRADOURO"));
				enderecoBean.setCep(rs.getString("CEP_LOGRADOURO"));
				enderecoBean.setTipoLogradouroBean(tipoLogradouroBean);
				enderecoBean.setBairroBean(bairroBean);
				enderecoBean.setCidadeBean(cidadeBean);
				enderecoBean.setEstadoBean(estadoBean);
				
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaEnderecoPorEstacionamento. Pilha: " + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return enderecoBean;
	}
		
	public int inserir(EnderecoBean obj) {
		
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs 				=	null; 
		int idEndereco				=	0;
		
		try {
		
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR_ENDERECO, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, obj.getNomeLogradouro());
			pstmt.setInt(2, obj.getNumero());
			pstmt.setString(3, obj.getCep());
			pstmt.setInt(4, obj.getTipoLogradouroBean().getId());
			pstmt.setInt(5, obj.getBairroBean().getId());
			pstmt.setInt(6, obj.getCidadeBean().getId());
			pstmt.setInt(7, obj.getEstadoBean().getId());
			pstmt.setInt(8, obj.getPaisBean().getId());
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
			    idEndereco = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo inserir. Pilha: " + e.getMessage());
			e.printStackTrace();
			return 0;
		} finally {
			DB.close(conn, pstmt, rs);
		}

		return idEndereco;
		
	}

	public boolean alterar(EnderecoBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		
		try {
		
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ALTERAR_ENDERECO);
			pstmt.setString(1, obj.getNomeLogradouro());
			pstmt.setInt(2, obj.getNumero());
			pstmt.setString(3, obj.getCep());
			pstmt.setInt(4, obj.getTipoLogradouroBean().getId());
			pstmt.setInt(5, obj.getBairroBean().getId());
			pstmt.setInt(6, obj.getCidadeBean().getId());
			pstmt.setInt(7, obj.getEstadoBean().getId());
			pstmt.setInt(8, obj.getPaisBean().getId());
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


	
}