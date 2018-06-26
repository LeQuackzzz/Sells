package sells.control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sells.dao.*;
import sells.entidade.Fornecedor;

@WebServlet("/FornecedorControl")
public class FornecedorControl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public FornecedorControl() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.getWriter().append("<html><h1>Acesse a pagina <a href=\".index.html\">index.html</a></h1></html");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String txtCnpj = request.getParameter("txtCnpj");
		String txtNome = request.getParameter("txtNome");
		String txtTel = request.getParameter("txtTel");
		String txtStatus = request.getParameter("txtStatus");
		String cmd = request.getParameter("cmd");
		
		List<Fornecedor> l = (List<Fornecedor>) getServletContext().getAttribute("FORNEC");
		if(l == null) {
			l = new ArrayList<>();
			getServletContext().setAttribute("FORNEC", l);
		}
		String message = null;
		IFornecedorDAO fornecedorDao = new FornecedorDAO();
		
		if("adicionar".equals(cmd)) {
			Fornecedor f = new Fornecedor();
			f.setNome(txtNome);
			f.setTel(txtTel);
			f.setStatus(Integer.parseInt(txtStatus));
			fornecedorDao.adicionar(f);
			
			response.sendRedirect("./FormFornecedorAdd.jsp");
		
		} else if("alterar".equals(cmd)) {
			Fornecedor f = new Fornecedor();
			f.setNome(txtNome);
			f.setCnpj(txtCnpj);
			f.setTel(txtTel);
			f.setStatus(Integer.parseInt(txtStatus));
			fornecedorDao.alterar(f, txtNome);
			
			
			response.sendRedirect("./FormFornecedorPesq.jsp");
		
		}else if("deletar".equals(cmd)) {
			fornecedorDao.deletar(txtNome);
			response.sendRedirect("./FormFornecedorPesq.jsp");
		
		}else if("pesquisar".equals(cmd)) {
			l = fornecedorDao.pesquisar(txtNome);
			if(l != null && l.size() > 0) {
				Fornecedor f = l.get(0);
				request.getSession().setAttribute("FORNEC", f);
			}
			response.sendRedirect("./FormFornecedorPesq.jsp");

			request.getSession().setAttribute("MESSAGE", message);
		}
		
		request.getSession().setAttribute("MESSAGE", message);
		
	}
	
	
}
