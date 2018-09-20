<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Pagina de Login</h1>
	<form action="../loginServlet">
	<p>Usuario:</p>
	<input type="text" name="usuario">
	<p>Contraseña:</p>
	<input name="contra" type="password" id="contra">
	<button type="submit">Ingresar</button>
	</form>	

      <%if(request.getParameter("badCredentials") != null) {%>
      	<p style="color:#FF0000">Usuario o Contraseña incorrecta.</p>
      <%} %>
	
	
</body>
</html>