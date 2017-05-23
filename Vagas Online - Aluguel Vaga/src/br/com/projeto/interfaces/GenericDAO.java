package br.com.projeto.interfaces;

import java.util.List;

public interface GenericDAO<T> {

	boolean inserir(T obj);	
	boolean alterar(T obj);
	boolean excluir(int id);
	T buscarPorId(int id);
	List<T> listaTodos();

}