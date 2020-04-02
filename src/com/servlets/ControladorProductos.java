package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private ModeloProducto modeloProductos;
	
	@Resource(name="jdbc/Productos")
	private DataSource miPool;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			modeloProductos = new ModeloProducto(miPool);
		}catch (Exception e) {
			throw new ServletException(e);
		}
	}




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		List<Producto> productos;
		try {
			productos = modeloProductos.getProductos();
			request.setAttribute("LISTAPRODUCTOS", productos);
			RequestDispatcher miRequestDispatcher = request.getRequestDispatcher("/ListaProductos.jsp");
			miRequestDispatcher.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
