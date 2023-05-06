package com.challenge.pruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.challenge.factory.ConnectionFactory;

public class PruebaEliminar {

	public static void main(String[] args) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();
		Statement statement = con.createStatement();
		statement.execute("DELETE FROM RESERVA WHERE ID = 1");
		statement.execute("DELETE FROM HUESPED WHERE ID = 1");
		System.out.println(statement.getUpdateCount());
	}
}
