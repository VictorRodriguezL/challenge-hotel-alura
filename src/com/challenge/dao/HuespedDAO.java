package com.challenge.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.challenge.modelo.Huesped;

public class HuespedDAO {
	
	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO HUESPED(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reserva_id)"
					+ " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				ejecutaRegistro(huesped, statement);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void ejecutaRegistro(Huesped huesped, PreparedStatement statement) {
		try {
			statement.setString(1, huesped.getNombre());
			statement.setString(2, huesped.getApellido());
			statement.setDate(3, huesped.getFechaNacimiento());
			statement.setString(4, huesped.getNacionalidad());
			statement.setString(5, huesped.getTelefono());
			statement.setInt(6, huesped.getReservaId());
			statement.execute();
			
			final ResultSet resultSet = statement.getGeneratedKeys();
				while (resultSet.next()) {
					huesped.setId(resultSet.getInt(1));
					System.out.println(
							String.format("Fue insertado el huesped %s", huesped));
				}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer reservaId, Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE HUESPED SET NOMBRE = ?, APELLIDO = ?, FECHA_NACIMIENTO = ?, NACIONALIDAD = ?,"
					+ " TELEFONO = ?, RESERVA_ID = ? WHERE ID = ?");
			statement.setString(1, nombre);
			statement.setString(2, apellido);
			statement.setDate(3, fechaNacimiento);
			statement.setString(4, nacionalidad);
			statement.setString(5, telefono);
			statement.setInt(6, reservaId);
			statement.setInt(7, id);
			statement.execute();
			return statement.getUpdateCount();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPED WHERE ID = ?");
				statement.setInt(1, id);
				statement.execute();
				return statement.getUpdateCount();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO,"
					+ " RESERVA_ID FROM HUESPED");
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"), resultSet.getDate("FECHA_NACIMIENTO"),
								resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"), resultSet.getInt("RESERVA_ID"));
						resultado.add(fila);
					}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public List<Huesped> listar(Integer reservaId) {
		List<Huesped> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO,"
					+ " RESERVA_ID FROM HUESPED WHERE RESERVA_ID = ?");
			statement.setInt(1, reservaId);
			statement.execute();
			final ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				Huesped fila = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"), resultSet.getDate("FECHA_NACIMIENTO"),
						resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"), resultSet.getInt("RESERVA_ID"));
				resultado.add(fila);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
}
