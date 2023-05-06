package com.challenge.controller;

import com.challenge.dao.UsuarioDAO;
import com.challenge.factory.ConnectionFactory;

public class UsuarioController {

	private UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public boolean validarUsuario(String nombre, String contrasenia) {
		return usuarioDAO.validarUsuario(nombre, contrasenia);
	}
}
