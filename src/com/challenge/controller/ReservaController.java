package com.challenge.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.challenge.dao.ReservaDAO;
import com.challenge.factory.ConnectionFactory;
import com.challenge.modelo.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}
	
	public int modificar(Date fechaEntrada, Date fechaSalida, BigDecimal valor, String formaPago, Integer id) {
		return reservaDAO.modificar(fechaEntrada, fechaSalida, valor, formaPago, id);
	}
	
	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}
	
	public List<Reserva> listar() {
		return reservaDAO.listar();
	}
	
	public List<Reserva> listar(Reserva reserva) {
		return reservaDAO.listar(reserva.getFechaEntrada());
	}
}
