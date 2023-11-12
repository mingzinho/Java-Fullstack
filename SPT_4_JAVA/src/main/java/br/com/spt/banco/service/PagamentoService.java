package br.com.spt.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.spt.banco.dao.PagamentoDao;
import br.com.spt.banco.exception.BadInfoException;
import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.factory.ConnectionFactory;
import br.com.spt.banco.model.Pagamento;

public class PagamentoService {
	
	private PagamentoDao pagamentoDao;
	
	public PagamentoService() throws ClassNotFoundException, SQLException {
		Connection con3 = ConnectionFactory.getConnection();
		pagamentoDao = new PagamentoDao(con3);

	}
	
	
	public void cadastrar(Pagamento pagamento) throws ClassNotFoundException, SQLException, BadInfoException {
		//validar(pagamento);
		pagamentoDao.cadastrar(pagamento);
	}
	
	
	public List<Pagamento> listar() throws ClassNotFoundException, SQLException{
		return pagamentoDao.listar();
	}
	
	public void remover(int idPagamento) throws ClassNotFoundException, SQLException, IdNotFoundException {
		pagamentoDao.remover(idPagamento);
	}

	
	public void atualizar(Pagamento pagamento) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		//validar(produto);
		pagamentoDao.atualizar(pagamento);
	}


}
