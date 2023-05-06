package com.challenge.modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private BigDecimal valor;
	private String formaPago;
	private List<Huesped> huespedes;
	
	public Reserva(int id, Date fechaEntrada, Date fechaSalida, BigDecimal valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reserva(Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = new BigDecimal(valor);
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public Date getFechaSalida() {
		return fechaSalida;
	}
	
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public String getFormaPago() {
		return formaPago;
	}
	
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	public List<Huesped> getHuespedes() {
		return huespedes;
	}
	
	public void setHuespedes(Huesped huesped) {
		if (this.huespedes == null) {
			this.huespedes = new ArrayList<Huesped>();
		}
		this.huespedes.add(huesped);
	}
	
	@Override
	public String toString() {
		return String.format(
				"{id: %s, fecha de entrada: %s, fecha de salida: %s, valor: %s, forma de pago: %s", 
				this.id, this.fechaEntrada, this.fechaSalida, this.valor, this.formaPago);
	}
}
