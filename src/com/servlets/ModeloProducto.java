package com.servlets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

public class ModeloProducto {

	private DataSource origenDatos;
	
	public ModeloProducto(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}
	
	public List<Producto> getProductos() throws Exception{
		List<Producto> productos = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultSet = null;
		
		miConexion = origenDatos.getConnection();
		
		String instruccionSql = "SELECT * FROM PRODUCTOS";
		miStatement = miConexion.createStatement();
		miResultSet = miStatement.executeQuery(instruccionSql);
		while(miResultSet.next()) {
			String c_art = miResultSet.getString("CODIGOARTICULO");
			String seccion = miResultSet.getString("SECCION");
			String n_art = miResultSet.getString("NOMBREARTICULO");
			double precio = miResultSet.getDouble("PRECIO");
			Date fecha = miResultSet.getDate("FECHA");
			int importado = miResultSet.getInt("IMPORTADO");
			String p_origen = miResultSet.getString("PAISDEORIGEN");
			
			Producto temProd = new Producto(c_art, seccion, n_art, precio, fecha, importado, p_origen);
			
			productos.add(temProd);
		}
		
		return productos;
	}
	
}
