package br.com.spt.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.spt.banco.dao.SeguroDao;
import br.com.spt.banco.exception.BadInfoException;
import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.factory.ConnectionFactory;
import br.com.spt.banco.model.Seguro;

public class SeguroService {
	
	private SeguroDao seguroDao;
	
	public SeguroService() throws ClassNotFoundException, SQLException {
		Connection con4 = ConnectionFactory.getConnection();
		seguroDao = new SeguroDao(con4);

	}
	
	
	public void cadastrar(Seguro seguro) throws ClassNotFoundException, SQLException, BadInfoException {
		//validar(seguro);
		seguroDao.cadastrar(seguro);
	}
	
	
	public List<Seguro> listar() throws ClassNotFoundException, SQLException{
		return seguroDao.listar();
	}
	
	public void remover(int idSeguro) throws ClassNotFoundException, SQLException, IdNotFoundException {
		seguroDao.remover(idSeguro);
	}

	
	public void atualizar(Seguro seguro) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		//validar(produto);
		seguroDao.atualizar(seguro);
	}


}
