package com.challenge.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_reservas?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("Alura");
		pooledDataSource.setPassword("challenge");
		pooledDataSource.setMaxPoolSize(10);
		
		this.dataSource = pooledDataSource;
	}
	
	public Connection recuperaConexion() {
		try {
		return this.dataSource.getConnection();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
