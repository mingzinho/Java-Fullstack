package br.com.spt.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.model.Seguro;

public class SeguroDao {
	
	private Connection con4;

	public SeguroDao(Connection con4) {
		this.con4 = con4;
	}

	public void cadastrar(Seguro seguro) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = con4.prepareStatement("INSERT INTO seguro (id_seguro, tipo_seguro, dt_inicio, dt_termino) "
				+ "values (?,?,?,?)");
		
		stm.setInt(1, seguro.getIdSeguro());
		stm.setString(2, seguro.getTipoSeguro());
		stm.setString(3, seguro.getDataInicio());
		stm.setString(4, seguro.getDataTermino());

		stm.executeUpdate();
	}

	public List<Seguro> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = con4.prepareStatement("select * from pagamento,seguro");

		ResultSet result = stm.executeQuery();
		List<Seguro> lista = new ArrayList<Seguro>();

		while (result.next()) {
			Seguro seguro = parse(result);
			lista.add(seguro);
		}

		return lista;
	}

	private Seguro parse(ResultSet result) throws SQLException {

		int idPagamento = result.getInt("Id do pagamento");
		double valorPagamento = result.getDouble("Valor do pagamento");
		String dataPagamento = result.getString("Data do pagamento");
		String formaPagamento = result.getString("Forma de pagamento");
		int quantidadeParcelas = result.getInt("Quantidade de parcelas");
		
		int idSeguro = result.getInt("ID do seguro");
		String tipoSeguro = result.getString("Tipo de seguro");
		String dataInicio = result.getString("Inicio do seguro");
		String dataTermino = result.getString("Termino do seguro");

		Seguro seguro = new Seguro(idPagamento, valorPagamento, dataPagamento, formaPagamento, quantidadeParcelas, 
				idSeguro, tipoSeguro, dataInicio, dataTermino);

		return seguro;
	}
	
	
	public void remover(int idSeguro) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = con4.prepareStatement("delete from seguro where id_seguro = ?");
		stm.setInt(1, idSeguro);
		
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Nome n�o encontrado para remo��o");
	}

	public void atualizar(Seguro seguro) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = con4.prepareStatement("update seguro set tipo_seguro = ? where id_seguro = ?");
		stm.setString(1, seguro.getTipoSeguro());
		stm.setInt(2, seguro.getIdSeguro());
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Nome n�o encontrado para atualizar");
	}



}
