package sells.dao;


import java.util.List;

import sells.entidade.Cliente;

public interface IClienteDAO {
	public void adiciona(Cliente c);
	List<Cliente> p_Cliente();
	List<Cliente> pesquisa(String rg);
	public void alterar(Cliente c, String rg);
	public void deletar(String rg);
}
