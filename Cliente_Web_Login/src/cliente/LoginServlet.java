package cliente;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import businessDelegate.BusinessDelegate;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -1894548383139548173L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String usuario = request.getParameter("usuario").toString();
		String contra = request.getParameter("contra").toString();
		
		try {
			boolean loginOk;
			if("nico".equals(usuario) && "123".equals(contra)) {
				loginOk = true;
			}else {
				loginOk = false;
			}
			
			if(loginOk) {
				//int nroUsuario = new BusinessDelegate().siguienteCodigoCliente();
				
				//response.getWriter().println("<h1>El ultimo numero del cliente es: "+new BusinessDelegate().siguienteCodigoCliente()+"</h1>");
				System.out.println(new BusinessDelegate().siguienteCodigoCliente());
				//new BusinessDelegate().holaMundo();
				//?nroUsuario="+nroUsuario
				//response.getWriter().println("<h1>entro al servlet</h1>");
				//System.out.println("entro al servlet");
				//LoginUtils.getInstancia().saveSession(request.getSession().getId(), clienteId);
				response.sendRedirect("./cliente/accesoCorrecto.jsp");
			}else {
				response.sendRedirect("./cliente/login.jsp?badCredentials=incorrecto");
			}
		} catch (Exception e) {
			response.sendRedirect("./jsp/cliente/login.jsp?badCredentials");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
