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
import sells.entidade.Cliente;
import sells.entidade.Produto;

@WebServlet("/ProdutoControl")
public class ProdutoControl  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public ProdutoControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.getWriter().append("<html><h1>Acesse a pagina <a href=\".index.html\">index.html</a></h1></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String txtCodigo = request.getParameter("txtCodigo");
		String txtNome = request.getParameter("txtNome");
		String txtPreco = request.getParameter("txtPreco");
		String txtValidade = request.getParameter("txtValidade");
		String cmd = request.getParameter("cmd");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Produto> l = (List<Produto>) getServletContext().getAttribute("PROD");

		if(l == null) {
			l = new ArrayList<>();
			getServletContext().setAttribute("PROD", l);
		}
		String message = null;
		IProdutoDAO produtoDao = new ProdutoDAO();

		if("adicionar".equals(cmd)) {
			Produto p = new Produto();
			p.setNome(txtNome);
			p.setPreco(Double.parseDouble(txtPreco));
			try {
				p.setValidade(sdf.parse(txtValidade));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			produtoDao.adicionar(p);
			message = String.format("Produto Adicionado! \n");
			response.sendRedirect("./FormProdutoAdd.jsp");
		} else if("alterar".equals(cmd)) {
			Produto p = new Produto();
			p.setNome(txtNome);
			p.setPreco(Double.parseDouble(txtPreco));
			try {
				p.setValidade(sdf.parse(txtValidade));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			produtoDao.alterar(p, txtNome);
			message = String.format("Produto Alterado! \n");
			response.sendRedirect("./FormProdutoPesq.jsp");
		}else if ("deletar".equals(cmd)) {
			produtoDao.deletar(txtNome);
			response.sendRedirect("./FormProdutoPesq.jsp");
		} else if ("pesquisar".equals(cmd)) {
			l = produtoDao.pesquisar(txtNome);
			if (l != null && l.size() > 0) {
				Produto p = l.get(0);
				request.getSession().setAttribute("PROD", p);
			}
			response.sendRedirect("./FormProdutoPesq.jsp");

			request.getSession().setAttribute("MESSAGE", message);

		}
	}

}
