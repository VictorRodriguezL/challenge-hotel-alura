package com.challenge.controller;

import java.sql.Date;
import java.util.List;

import com.challenge.dao.HuespedDAO;
import com.challenge.factory.ConnectionFactory;
import com.challenge.modelo.Huesped;

public class HuespedController {

	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Huesped huesped) {
		huespedDAO.guardar(huesped);
	}
	
	public int modificar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer reservaId, Integer id) {
		return huespedDAO.modificar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, reservaId, id);
	}
	
	public int eliminar(Integer id) {
		return huespedDAO.eliminar(id);
	}
	
	public List<Huesped> listar() {
		return huespedDAO.listar();
	}
	
	public List<Huesped> listar(Integer reservaId) {
		return huespedDAO.listar(reservaId);
	}
}
