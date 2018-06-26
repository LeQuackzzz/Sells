package sells.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sells.entidade.Cliente;
import sells.entidade.Produto;

public class ProdutoDAO implements IProdutoDAO{

	@Override
	public void adicionar(Produto p) {
		Connection con = Conexoes.getInstancia().openConnection();
		String sql = "insert into Produto (nome_prod, preco_prod, validade_prod)"
				+ " values (?,?,?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getPreco());
			java.sql.Date d = new java.sql.Date( p.getValidade().getTime() );
			ps.setDate(3, d);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Conexoes.getInstancia().closeConnection();
	}

	@Override
	public List<Produto> pesquisar(String nome) {
		Connection con = Conexoes.getInstancia().openConnection();
		List<Produto> l = new ArrayList<>();
		String sql = "select * from PRODUTO where nome_prod like ?";
		CallableStatement cs;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nome+'%');
			ResultSet rs = stmt.executeQuery();
			cs = con.prepareCall("{CALL p_Produto(?)}");
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.execute();
			Produto p = new Produto();
			//NOMES IGUAIS DA TABELA DO BD
			//EX.: rg_client
			p.setTotal(cs.getInt(1));
			l.add(p);
			while(rs.next()) {
				//NOMES IGUAIS DA TABELA DO BD
				//EX.: cod_prod
				p.setCodigo(rs.getString("cod_prod"));
				p.setNome(rs.getString("nome_prod"));
				p.setPreco(rs.getDouble("preco_prod"));
				p.setValidade(rs.getDate("validade_prod"));
				l.add(p);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}

	@Override
	public void alterar(Produto p, String nome) {
		Connection con = Conexoes.getInstancia().openConnection();
		 String sql = "UPDATE PRODUTO " +  
                " SET nome_prod = ?," +                     
                " preco_prod = ?," +  
                " validade_prod = ? " +  
                " WHERE nome_prod like ?"; 

		
		try {
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setString(4, nome);
			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getPreco());
			java.sql.Date d = new java.sql.Date( p.getValidade().getTime() );
			ps.setDate(3, d);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Conexoes.getInstancia().closeConnection();
		
	}

	@Override
	public void deletar(String nome) {
		Connection con = Conexoes.getInstancia().openConnection();
		String sql = "DELETE FROM produto WHERE nome_prod = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nome);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Conexoes.getInstancia().closeConnection();
		
	}

}
