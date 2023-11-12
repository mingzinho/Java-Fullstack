package br.com.spt.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.spt.banco.dao.SinistroDao;
import br.com.spt.banco.exception.BadInfoException;
import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.factory.ConnectionFactory;
import br.com.spt.banco.model.Sinistro;

public class SinistroService {
	
	private SinistroDao sinistroDao;
	
	public SinistroService() throws ClassNotFoundException, SQLException {
		Connection con5 = ConnectionFactory.getConnection();
		sinistroDao = new SinistroDao(con5);

	}
	
	
	public void cadastrar(Sinistro sinistro) throws ClassNotFoundException, SQLException, BadInfoException {
		//validar(sinistro);
		sinistroDao.cadastrar(sinistro);
	}
	
	
	public List<Sinistro> listar() throws ClassNotFoundException, SQLException{
		return sinistroDao.listar();
	}
	
	public void remover(int idSinistro) throws ClassNotFoundException, SQLException, IdNotFoundException {
		sinistroDao.remover(idSinistro);
	}

	
	public void atualizar(Sinistro sinistro) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		//validar(produto);
		sinistroDao.atualizar(sinistro);
	}


}
