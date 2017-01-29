package br.edu.ifpb.memoriam.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Operadora;
import br.edu.ifpb.memoriam.entity.Usuario;
import br.edu.ifpb.memoriam.facade.ContatoController;
import br.edu.ifpb.memoriam.facade.LoginController;
import br.edu.ifpb.memoriam.facade.OperadoraController;
import br.edu.ifpb.memoriam.facade.Resultado;


/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet("/controller.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContatoController contatoCtrl = new ContatoController();  
	private OperadoraController operadoraCtrl = new OperadoraController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ContatoController contatoCtrl = new ContatoController();
		String proxPagina = null;
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");	
		this.getServletContext().removeAttribute("msgs");
		
		String operacao = request.getParameter("op");
		if (operacao == null){
			this.getServletContext().setAttribute("msgs", "Operação (op) não especificada na requisição!");
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}
	
		switch(operacao){
			case "conctt":
				List<Contato> contatos = contatoCtrl.consultar(usuario);
				request.setAttribute("contatos", contatos);
				proxPagina = "contato/consulta.jsp";
			break;
			
			case "edtctt":
				Contato contato = new Contato();
				contato = contatoCtrl.buscar(request.getParameterMap());
				request.setAttribute("contato", contato);
				proxPagina = "contato/cadastro.jsp";
			break;
			
			case "conopr":
				List<Operadora> operadoras = operadoraCtrl.consultar();
				request.setAttribute("operadoras", operadoras);
				proxPagina = "operadora/consulta.jsp";
			break;
			
			case "edtopr":
				Operadora operadora = new Operadora();
				operadora = operadoraCtrl.buscar(request.getParameterMap());
				request.setAttribute("operadora", operadora);
				proxPagina = "operadora/cadastro.jsp";
			break;	
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(proxPagina);
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			
		this.getServletContext().removeAttribute("msgs");
		LoginController loginCtrl = new LoginController();
		HttpSession session = request.getSession();
		Resultado resultado = new Resultado();
		String paginaSucessoContato = "controller.do?op=conctt";
		String paginaSucessoOperadora = "controller.do?op=conopr";
		String proxPagina = null;	
		String operacao = request.getParameter("op");
		
		if (operacao == null){
			this.getServletContext().setAttribute("msgs", new String[]{"Operação(op) não especificada na requisição!"});
			response.sendRedirect(request.getHeader("Referer"));
			return;
		}
		
		
		switch(operacao){
			case "cadctt":
				resultado = contatoCtrl.cadastrar(request.getParameterMap());
				if (resultado.isErro()){
					request.setAttribute("contato", (Contato) resultado.getEntidade());
					request.setAttribute("msgs",resultado.getMensagens());
					proxPagina = "contato/cadastro.jsp";	
				}else{
					proxPagina = paginaSucessoContato;					
				}					
			break;
			
			case "cadopr":
				resultado = operadoraCtrl.cadastrar(request.getParameterMap());
				if (resultado.isErro()){
					proxPagina = "operadora/cadastro.jsp";
					request.setAttribute("operadora", (Operadora) resultado.getEntidade());
					request.setAttribute("msgs", resultado.getMensagens());
				}else{
					proxPagina = paginaSucessoOperadora;
				}	
			break;
			
			case "excctt":
				resultado = contatoCtrl.excluir(request.getParameterMap());
				if (resultado.isErro()){
					request.setAttribute("msg", resultado.getMensagens());
				}else{
					proxPagina = paginaSucessoContato;
					request.setAttribute("msgs", resultado.getMensagens());
				}
			break;
			
			case "login":
				String paginaSucesso = "controller.do?op=conctt";
				resultado = loginCtrl.isValido(request.getParameterMap());
				
				if (resultado.isErro()){
					request.setAttribute("msgs", resultado.getMensagens());
					proxPagina = "login/login.jsp";
				}else{
					String lembrar = request.getParameter("lembrar");
					Usuario usuarioLogado = (Usuario) resultado.getEntidade();
					session.setAttribute("usuario",usuarioLogado);
					proxPagina = paginaSucesso;
					
					if (lembrar != null) { 
						Cookie c = new Cookie("loginCookie", usuarioLogado.getEmail());
						c.setMaxAge(-1); response.addCookie(c); 
					} else { 
						for (Cookie cookie : request.getCookies()) { 
							if (cookie.getName().equals("loginCookie")) {
								cookie.setValue(null); 
								cookie.setMaxAge(0); 
								response.addCookie(cookie); 
							} 
						}
					}			
				}
				break;	
				
			case "logout":
				proxPagina = "login/login.jsp";
				session.invalidate();
				break;

			default:
				request.setAttribute("erro", "Operação não especificada no servlet");
				proxPagina = "erro/erro.jsp";				
		}
		
		if (resultado.isErro()){
			RequestDispatcher dispatcher = request.getRequestDispatcher(proxPagina);
			dispatcher.forward(request, response);
		}else{
			response.sendRedirect(proxPagina);
		}
		
	}

}
