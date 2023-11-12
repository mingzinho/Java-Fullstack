package br.com.spt.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.spt.banco.dao.ClienteDao;
import br.com.spt.banco.exception.BadInfoException;
import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.factory.ConnectionFactory;
import br.com.spt.banco.model.Cliente;

public class ClienteService {
	
	private ClienteDao clienteDao;
	
	public ClienteService() throws ClassNotFoundException, SQLException {
		Connection con1 = ConnectionFactory.getConnection();
		clienteDao = new ClienteDao(con1);

	}
	
	
	public void cadastrar(Cliente cliente) throws ClassNotFoundException, SQLException, BadInfoException {
		//validar(cliente);
		clienteDao.cadastrar(cliente);
	}
	
	
	public List<Cliente> listar() throws ClassNotFoundException, SQLException{
		return clienteDao.listar();
	}
	
	public void remover(int idCliente) throws ClassNotFoundException, SQLException, IdNotFoundException {
		clienteDao.remover(idCliente);
	}

	
	public void atualizar(Cliente cliente) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		//validar(produto);
		clienteDao.atualizar(cliente);
	}


}
