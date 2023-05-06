package com.challenge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	private Connection con;
	
	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	public boolean validarUsuario(String nombre, String contrasenia) {
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT USUARIO, CONTRASENIA FROM USUARIOS WHERE USUARIO = ? AND CONTRASENIA = ?");
			statement.setString(1, nombre);
			statement.setString(2, contrasenia);
			statement.execute();
			final ResultSet resultSet = statement.getResultSet();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
