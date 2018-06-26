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

@WebServlet("/ClienteControl")
public class ClienteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClienteControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("<html><h1>Acesse a pagina <a href=\"./index.html\">index.html</a></h1></html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txtRg = request.getParameter("txtRg");
		String txtNome = request.getParameter("txtNome");
		String txtCpf = request.getParameter("txtCpf");
		String txtDdn = request.getParameter("txtDdn");
		String txtEnde = request.getParameter("txtEnde");
		String txtNumend = request.getParameter("txtNumend");
		String txtTel = request.getParameter("txtTel");
		String cmd = request.getParameter("cmd");
		String total = request.getParameter("txtTotal");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		List<Cliente> l = (List<Cliente>) getServletContext().getAttribute("CLI");
		if (l == null) {
			l = new ArrayList<>();
			getServletContext().setAttribute("CLI", l);
		}
		String message = null;
		IClienteDAO clienteDao = new ClienteDAO();
		l = clienteDao.pesquisa(txtRg);
		if("adicionar".equals(cmd)) {
			
		Cliente c = new Cliente();
		c.setRg(txtRg);
		c.setNome(txtNome);
		c.setCpf(txtCpf);
		try {
			c.setDdn(sdf.parse(txtDdn));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setEnde(txtEnde);
		c.setNum(txtNumend);
		c.setTel(txtTel);
		clienteDao.adiciona( c );
		response.sendRedirect("./FormClienteAdd.jsp");
		} else if ("pesquisar".equals(cmd)) {
			l = clienteDao.pesquisa(txtRg);
			
			if (l != null && l.size() > 0) {
				Cliente c = l.get(0);
				request.getSession().setAttribute("CLI", c);
				
			} 
			
			response.sendRedirect("./FormClientePesq.jsp");
		} else if ("alterar".equals(cmd)) {
			Cliente c = new Cliente();
			c.setRg(txtRg);
			c.setNome(txtNome);
			c.setCpf(txtCpf);
			try {
				c.setDdn(sdf.parse(txtDdn));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.setEnde(txtEnde);
			c.setNum(txtNumend);
			c.setTel(txtTel);
			clienteDao.alterar( c,txtRg );
			response.sendRedirect("./FormClientePesq.jsp");
		} else if ("deletar".equals(cmd)) {
			clienteDao.deletar(txtRg);
			response.sendRedirect("./FormClientePesq.jsp");
		}
		
		
		request.getSession().setAttribute("MESSAGE", message);
		
	}

}
