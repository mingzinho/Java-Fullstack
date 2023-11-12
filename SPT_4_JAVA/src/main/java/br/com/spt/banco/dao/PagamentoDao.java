package br.com.spt.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.model.Pagamento;

public class PagamentoDao {
	
	private Connection con3;

	public PagamentoDao(Connection con3) {
		this.con3 = con3;
	}

	public void cadastrar(Pagamento pagamento) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = con3.prepareStatement("INSERT INTO pagamento (id_pag, valor_pag, dt_pag, forma_pag, qtd_parcelas) "
				+ "values (?,?,?,?,?)");
		
		stm.setInt(1, pagamento.getIdPagamento());
		stm.setDouble(2, pagamento.getValorPagamento());
		stm.setString(3, pagamento.getDataPagamento());
		stm.setString(4, pagamento.getFormaPagamento());
		stm.setInt(5, pagamento.getQuantidadeParcelas());

		stm.executeUpdate();
	}

	public List<Pagamento> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = con3.prepareStatement("select * from pagamento");

		ResultSet result = stm.executeQuery();
		List<Pagamento> lista = new ArrayList<Pagamento>();

		while (result.next()) {
			Pagamento pagamento = parse(result);
			lista.add(pagamento);
		}

		return lista;
	}

	private Pagamento parse(ResultSet result) throws SQLException {

		int idPagamento = result.getInt("Id do pagamento");
		double valorPagamento = result.getDouble("Valor do pagamento");
		String dataPagamento = result.getString("Data do pagamento");
		String formaPagamento = result.getString("Forma de pagamento");
		int quantidadeParcelas = result.getInt("Quantidade de parcelas");

		Pagamento pagamento = new Pagamento(idPagamento, valorPagamento, dataPagamento, formaPagamento, quantidadeParcelas);

		return pagamento;
	}
	
	
	public void remover(int idPagamento) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = con3.prepareStatement("delete from pagamento where id_pag = ?");
		stm.setInt(1, idPagamento);
		
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Nome n�o encontrado para remo��o");
	}

	public void atualizar(Pagamento pagamento) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = con3.prepareStatement("update pagamento set valor_pag = ? where id_pag = ?");
		stm.setDouble(1, pagamento.getValorPagamento());
		stm.setInt(2, pagamento.getIdPagamento());
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Nome n�o encontrado para atualizar");
	}



}
