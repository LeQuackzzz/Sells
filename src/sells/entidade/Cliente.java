package sells.entidade;

import java.util.Date;

public class Cliente {
	private String rg;
	private String nome;
	private String cpf;
	private Date ddn;
	private String ende;
	private String num;
	private String tel;
	private int total;
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDdn() {
		return ddn;
	}
	public void setDdn(Date ddn) {
		this.ddn = ddn;
	}
	public String getEnde() {
		return ende;
	}
	public void setEnde(String ende) {
		this.ende = ende;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
