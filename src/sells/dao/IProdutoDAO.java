package sells.dao;

import java.util.List;

import sells.entidade.Produto;

public interface IProdutoDAO {

	public void adicionar(Produto p);
	List<Produto> pesquisar(String nome);
	public void alterar(Produto p, String nome);
	public void deletar(String nome);
	
}
