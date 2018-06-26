package sells.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Set;

import sells.entidade.Cliente;



public class ClienteDAO implements IClienteDAO{


	@Override
	public void adiciona(Cliente c) {
		Connection con = Conexoes.getInstancia().openConnection();
		String sql = "insert into Cliente (rg_client, nome_client, cpf_client, ddn_client, "
				+ "endereco_client, numEnd_client, tel_client) values(?,?,?,?,?,?,?)";

		
		try {
			PreparedStatement ps = con.prepareStatement( sql );


			ps.setString(1, c.getRg());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getCpf());
			java.sql.Date d = new java.sql.Date( c.getDdn().getTime() );
			ps.setDate(4, d);
			ps.setString(5, c.getEnde());
			ps.setString(6, c.getNum());
			ps.setString(7, c.getTel());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Conexoes.getInstancia().closeConnection();
	}

	@Override
	public List<Cliente> pesquisa(String rg) {
		Connection con = Conexoes.getInstancia().openConnection();
		List<Cliente> l = new ArrayList<>();
		String sql = "select * from CLIENTE where rg_client=?";
		CallableStatement cs;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, rg);
			ResultSet rs = stmt.executeQuery();
			cs = con.prepareCall("{CALL p_cliente(?)}");
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.execute();
			
				Cliente c = new Cliente();
				//NOMES IGUAIS DA TABELA DO BD
				//EX.: rg_client
				c.setTotal(cs.getInt(1));
				l.add(c);
			while(rs.next()){
				
				//NOMES IGUAIS DA TABELA DO BD
				//EX.: rg_client
				c.setRg(rs.getString("rg_client"));
				c.setNome(rs.getString("nome_client"));
				c.setCpf(rs.getString("cpf_client"));
				c.setDdn(rs.getDate("ddn_client"));
				c.setEnde(rs.getString("endereco_client"));
				c.setNum(rs.getString("numEnd_client"));
				c.setTel(rs.getString("tel_client"));
				l.add(c);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
		
	}
	
	public void alterar(Cliente c, String rg) {
		Connection con = Conexoes.getInstancia().openConnection();
		 String sql = "UPDATE CLIENTE " +  
                 " SET rg_client = ?, " +  
                 " nome_client = ?," +                     
                 " cpf_client = ?," +  
                 " ddn_client = ?, " +  
                 " endereco_client = ?, " +  
                 " numEnd_client = ?, " +  
                 " tel_client = ? " +  
                 " WHERE rg_client = ?"; 

		
		try {
			PreparedStatement ps = con.prepareStatement( sql );
			ps.setString(8, rg);

			ps.setString(1, c.getRg());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getCpf());
			java.sql.Date d = new java.sql.Date( c.getDdn().getTime() );
			ps.setDate(4, d);
			ps.setString(5, c.getEnde());
			ps.setString(6, c.getNum());
			ps.setString(7, c.getTel());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Conexoes.getInstancia().closeConnection();
	}
	
	public void deletar(String rg) {
		Connection con = Conexoes.getInstancia().openConnection();
		String sql = "DELETE FROM cliente WHERE rg_client=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, rg);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Conexoes.getInstancia().closeConnection();
	}
	

	
	public List<Cliente> p_Cliente() {
		Connection con = Conexoes.getInstancia().openConnection();
		List<Cliente> l = new ArrayList<>();
		CallableStatement cs;   
		
		try {
			cs = con.prepareCall("{CALL p_cliente(?)}");
			   
			//Informa o tipo de retorno
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
 
			// Executa a Stored procedure
			cs.execute();
			// Faz a leitura do retorno
			
				Cliente c = new Cliente();
				//NOMES IGUAIS DA TABELA DO BD
				//EX.: rg_client
				c.setTotal(cs.getInt(1));
				l.add(c);
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
		
	}

}
