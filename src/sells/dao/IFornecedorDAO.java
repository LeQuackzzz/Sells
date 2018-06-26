package sells.dao;

import java.util.List;

import sells.entidade.Fornecedor;

public interface IFornecedorDAO {

	public void adicionar(Fornecedor f);
	List<Fornecedor> pesquisar(String nome);
	public void alterar(Fornecedor f, String nome);
	public void deletar(String nome);
	
}
