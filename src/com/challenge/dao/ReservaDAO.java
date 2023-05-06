package com.challenge.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.challenge.modelo.Reserva;

public class ReservaDAO {
	
	private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO RESERVA(fecha_entrada, fecha_salida, valor, forma_pago)"
					+ " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ejecutaRegistro(reserva, statement);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void ejecutaRegistro(Reserva reserva, PreparedStatement statement) {
		try {
			statement.setDate(1, reserva.getFechaEntrada());
			statement.setDate(2, reserva.getFechaSalida());
			statement.setBigDecimal(3, reserva.getValor());
			statement.setString(4, reserva.getFormaPago());
			statement.execute();
			
			final ResultSet resultSet = statement.getGeneratedKeys();
			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println(
						String.format("Fue insertado el huesped %s", reserva));
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(Date fechaEntrada, Date fechaSalida, BigDecimal valor, String formaPago, Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVA SET FECHA_ENTRADA = ?, FECHA_SALIDA = ?, VALOR = ?, FORMA_PAGO = ?"
					+ "  WHERE ID = ?");
			statement.setDate(1, fechaEntrada);
			statement.setDate(2, fechaSalida);
			statement.setBigDecimal(3, valor);
			statement.setString(4, formaPago);
			statement.setInt(5, id);
			statement.execute();
			return statement.getUpdateCount();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVA WHERE ID = ?");
			statement.setInt(1, id);
			statement.execute();
			return statement.getUpdateCount();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> listar() {
		List<Reserva> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO"
					+ " FROM RESERVA");
			statement.execute();
			final ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				Reserva fila = new Reserva(resultSet.getInt("ID"), resultSet.getDate("FECHA_ENTRADA"), resultSet.getDate("FECHA_SALIDA"), resultSet.getBigDecimal("VALOR"),
						resultSet.getString("FORMA_PAGO"));
				resultado.add(fila);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public List<Reserva> listar(Date fechaEntrada) {
		List<Reserva> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO"
					+ " FROM RESERVA WHERE FECHA_ENTRADA = ?");
			statement.setDate(1, fechaEntrada);
			statement.execute();
			final ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				Reserva fila = new Reserva(resultSet.getInt("ID"), resultSet.getDate("FECHA_ENTRADA"), resultSet.getDate("FECHA_SALIDA"), resultSet.getBigDecimal("VALOR"),
						resultSet.getString("FORMA_PAGO"));
				resultado.add(fila);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
}
