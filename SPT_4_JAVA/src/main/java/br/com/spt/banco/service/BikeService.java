package br.com.spt.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.spt.banco.dao.BikeDao;
import br.com.spt.banco.exception.BadInfoException;
import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.factory.ConnectionFactory;
import br.com.spt.banco.model.Bike;

public class BikeService {
	
	private BikeDao bikeDao;
	
	public BikeService() throws ClassNotFoundException, SQLException {
		Connection con2 = ConnectionFactory.getConnection();
		bikeDao = new BikeDao(con2);

	}
	
	
	public void cadastrar(Bike bike) throws ClassNotFoundException, SQLException, BadInfoException {
		//validar(bike);
		bikeDao.cadastrar(bike);
	}
	
	
	public List<Bike> listar() throws ClassNotFoundException, SQLException{
		return bikeDao.listar();
	}
	
	public void remover(int idBike) throws ClassNotFoundException, SQLException, IdNotFoundException {
		bikeDao.remover(idBike);
	}

	
	public void atualizar(Bike bike) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		//validar(produto);
		bikeDao.atualizar(bike);
	}


}
