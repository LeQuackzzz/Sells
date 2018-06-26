<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="sells.entidade.Cliente, java.text.SimpleDateFormat"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Sells</title>
<style type="text/css">
.campo1 {
	position: absolute;
	left: 300px;
}

.inp_cinza {
	background-color: #d3d3d3;
}

@import url(https://fonts.googleapis.com/css?family=Roboto:400,300,600,400italic);

*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
  -moz-font-smoothing: antialiased;
  -o-font-smoothing: antialiased;
  font-smoothing: antialiased;
  text-rendering: optimizeLegibility;
}
body {
	font-family: "Roboto", Helvetica, Arial, sans-serif;
	font-weight: 100;	font-size: 12px;
	line-height: 30px;
	color: #777;
	background: #e0e0e0;
}

.container {
	max-width: 400px;
	width: 100%;
	margin: 0 auto;
	position: relative;
}

#contact input[type="text"], #contact input[type="email"], #contact input[type="tel"],
	#contact input[type="url"], #contact textarea, #contact button[type="submit"]
	{
	font: 400 12px/16px "Roboto", Helvetica, Arial, sans-serif;
}

#contact {
	background: #F9F9F9;
	padding: 25px;
	margin: 150px 0;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
}

#contact h3 {
	display: block;
	font-size: 30px;
	font-weight: 300;
	margin-bottom: 10px;
}

#contact h4 {
	margin: 5px 0 15px;
	display: block;
	font-size: 13px;
	font-weight: 400;
}

fieldset {
	border: medium none !important;
	margin: 0 0 10px;
	min-width: 100%;
	padding: 0;
	width: 100%;
}

#contact input[type="text"], #contact input[type="email"], #contact input[type="tel"],
	#contact input[type="url"], #contact textarea {
	width: 100%;
	border: 1px solid #ccc;
	background: #FFF;
	margin: 0 0 5px;
	padding: 10px;
}

#contact input[id="teste"] {
	width: 70%;
	border: 1px solid #ccc;
	background: #FFF;
	margin: 0 0 5px;
	padding: 10px;
}


contact input[id="proc"] {
	width: 25%;
	border: 1px solid #ccc;
	background: #d3d3d3;
	margin: 0 0 5px;
	padding: 10px;
}

#contact input[type="text"]:hover, #contact input[type="email"]:hover,
	#contact input[type="tel"]:hover, #contact input[type="url"]:hover,
	#contact textarea:hover {
	-webkit-transition: border-color 0.3s ease-in-out;
	-moz-transition: border-color 0.3s ease-in-out;
	transition: border-color 0.3s ease-in-out;
	border: 1px solid #aaa;
}

#contact textarea {
	height: 100px;
	max-width: 100%;
	resize: none;
}

#contact button[id="alt"]{
	cursor: pointer;
	width: 25%;
	border: none;
	background: #4CAF50;
	color: #FFF;
	margin: 0 0 5px;
	padding: 10px;
	font-size: 15px;
}

#contact button[id="volt"]{
	cursor: pointer;
	width: 25%;
	border: none;
	background: #bcbcbc;
	color: #FFF;
	margin: 0 0 5px;
	padding: 10px;
	font-size: 15px;
	float: right;
}

#contact button[id="del"] {
	cursor: pointer;
	width: 25%;
	border: none;
	background: #d80000;
	color: #FFF;
	margin: 0 0 5px;
	padding: 10px;
	font-size: 15px;
}

#contact button[id="pesq"]{
	cursor: pointer;
	width: 25%;
	border: none;
	background: #4CAF50;
	color: #FFF;
	margin: 0 0 5px;
	padding: 10px;
	
	font-size: 15px;
	float: right;
}

#contact button[type="submit"]:hover {
	background: #43A047;
	-webkit-transition: background 0.3s ease-in-out;
	-moz-transition: background 0.3s ease-in-out;
	transition: background-color 0.3s ease-in-out;
}

#contact button[id="del"]:hover {
	background: #af0000;
	-webkit-transition: background 0.3s ease-in-out;
	-moz-transition: background 0.3s ease-in-out;
	transition: background-color 0.3s ease-in-out;
}

#contact button[id="volt"]:hover {
	background: #a8a8a8;
	-webkit-transition: background 0.3s ease-in-out;
	-moz-transition: background 0.3s ease-in-out;
	transition: background-color 0.3s ease-in-out;
}

#contact button[type="submit"]:active {
	box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5);
}

.copyright {
	text-align: center;
}

#contact input:focus, #contact textarea:focus {
	outline: 0;
	border: 1px solid #aaa;
}

::-webkit-input-placeholder {
	color: #888;
}

:-moz-placeholder {
	color: #888;
}

::-moz-placeholder {
	color: #888;
}

:-ms-input-placeholder {
	color: #888;
}
</style>
<script language="JavaScript" type="text/javascript">
	
<%@ page import="sells.control.ClienteControl" import="sells.dao.ClienteDAO" %>
	function mascaraData(dt) {
		var txtDdn = dt.value;
		if (txtDdn.length == 2) {
			txtDdn = txtDdn + '/';
			document.forms[0].txtDdn.value = txtDdn;
			return true;
		}

		if (txtDdn.length == 5) {
			txtDdn = txtDdn + '/';
			document.forms[0].txtDdn.value = txtDdn;
			return true;
		}
	}

	function confirmacao() {
		if (confirm("Tem certeza que deseja excluir?"))
			alert("Cliente excluído!");
	}
	
	function alterar(){
		alert("Alteração concluída");
	}
	
	
</script>
</head>
<body>
	<%
		String msg = (String) session.getAttribute("MESSAGE");
		session.setAttribute("MESSAGE", null);

		Cliente cli = (Cliente) session.getAttribute("CLI");
		session.setAttribute("CLI", null);

		
			
		String dt;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (cli == null) {
			cli = new Cliente();
		}

		if (cli.getDdn() == null) {
			dt = "";
		} else {
			dt = sdf.format(cli.getDdn());
		}

		if (cli.getRg() == null) {
			cli.setRg("");
			cli.setCpf("");
			cli.setEnde("");
			cli.setNome("");
			cli.setNum("");
			cli.setTel("");
		}
		if (msg != null) {
	%>
	<div class="alert alert-danger" role="alert">
		<%=msg%>
	</div>
	<%
		}
	%>

	<div class="container">
		<form id="contact" action="./ClienteControl" method="post">
			<h3 id="FormClientePesq">Pesquisar Cliente</h3>
			<h4>Procure o cliente através do RG do mesmo.
			</h4>
			<fieldset>
				<label>CLIENTES CADASTRADOS: </label>
				<input id="proc" type="text" readonly name="txtTotal"
					value="<%=cli.getTotal()%>" style="background: #d3d3d3; width: 10%"/>
			</fieldset>
			<fieldset>
				<label>RG</label> 
			</fieldset>
			<fieldset>
				<input id="teste" type="text" name="txtRg" maxlength="9"
					value="<%=cli.getRg()%>"/>
					<button id="pesq" class="btn btn-danger" name="cmd" type="submit"
					value="pesquisar">Pesquisar</button>
			</fieldset>
			<fieldset>
				<label>Nome</label> <input type="text" name="txtNome"
					value="<%=cli.getNome()%>" />
			</fieldset>
			<fieldset>
				<label>CPF</label> <input type="text" name="txtCpf" maxlength="11"
					value="<%=cli.getCpf()%>"/>
			</fieldset>
			<fieldset>
				<label>Data de Nascimento</label> <input type="text" name="txtDdn"
					OnKeyUp="mascaraData(this);" maxlength="10" value="<%=dt%>"
					 />
			</fieldset>
			<fieldset>
				<label>Endereço</label> <input type="text" name="txtEnde"
					value="<%=cli.getEnde()%>" tabindex="5" />
			</fieldset>
			<fieldset>
				<label>Numero do Endereço</label> <input type="text"
					name="txtNumend" value="<%=cli.getNum()%>" />
			</fieldset>
			<fieldset>
				<label>Telefone</label> <input type="text" name="txtTel"
					maxlength="11" value="<%=cli.getTel()%>"  />
			</fieldset>
			<fieldset>
				<button id="alt" class="btn btn-danger" name="cmd" type="submit"
					value="alterar" onclick="alterar()">Alterar</button>
				<button id="del" class="btn btn-danger" name="cmd" type="submit"
					value="deletar" onclick="return confirmacao()">Deletar</button>
				
			</fieldset>
			<fieldset>
				<br><a href="index.html"><button id="volt" class="btn btn-danger" name="cmd" type="button"
						value="voltar">Voltar</button></a>
			</fieldset>
		</form>
	</div>
</body>
</html>