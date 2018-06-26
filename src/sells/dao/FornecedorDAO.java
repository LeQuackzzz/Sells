package sells.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sells.entidade.Cliente;
import sells.entidade.Fornecedor;

public class FornecedorDAO implements IFornecedorDAO{
	
	@Override
	public void adicionar(Fornecedor f) {
		Connection con = Conexoes.getInstancia().openConnection();
		String sql = "insert into Fornecedor (nome_fornec, tel_fornec," 
				+ "status_fornec) values(?,?,?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, f.getNome());
			ps.setString(2, f.getTel());
			ps.setInt(3, f.getStatus());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Conexoes.getInstancia().closeConnection();
	}

	@Override
	public List<Fornecedor> pesquisar(String nome) {
		Connection con = Conexoes.getInstancia().openConnection();
		List<Fornecedor> l = new ArrayList<>();
		String sql = "select * from FORNECEDOR where nome_fornec like ?";
		CallableStatement cs;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nome+"%");
			ResultSet rs = stmt.executeQuery();
			cs = con.prepareCall("{CALL ornec(?)}");
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.execute();
			Fornecedor f = new Fornecedor();
			//NOMES IGUAIS DA TABELA DO BD
			//EX.: rg_client
			f.setTotal(cs.getInt(1));
			l.add(f);
			
			while(rs.next()) {
				//NOMES IGUAIS DA TABELA DO BD
				//EX.: cnpj_fornec
				
				f.setCnpj(rs.getString("cnpj_fornec"));
				f.setNome(rs.getString("nome_fornec"));
				f.setTel(rs.getString("tel_fornec"));
				f.setStatus(rs.getInt("status_fornec"));
				l.add(f);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}

	@Override
	public void alterar(Fornecedor f, String nome) {
		Connection con = Conexoes.getInstancia().openConnection();
		 String sql = "UPDATE FORNECEDOR " +  
                " SET nome_fornec = ?, " +  
                " tel_fornec = ?," +  
                " status_fornec = ? " +  
                " WHERE nome_fornec = ?"; 

		
		try {
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setString(4, nome);
			
			ps.setString(1, f.getNome());
			ps.setString(2, f.getTel());
			ps.setInt(3, f.getStatus());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Conexoes.getInstancia().closeConnection();
		
	}

	@Override
	public void deletar(String nome) {
		Connection con = Conexoes.getInstancia().openConnection();
		String sql = "DELETE FROM fornecedor WHERE nome_fornec = ?";
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
