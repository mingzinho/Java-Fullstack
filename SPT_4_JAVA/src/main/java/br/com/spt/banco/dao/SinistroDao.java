package br.com.spt.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.model.Sinistro;

public class SinistroDao {

	private Connection con5;

	public SinistroDao(Connection con5) {
		this.con5 = con5;
	}

	public void cadastrar(Sinistro sinistro) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = con5.prepareStatement("INSERT INTO sinistro (id_sinistro, dt_ocorrencia, desc_sinistro) "
				+ "values (?,?,?)");
		
		stm.setInt(1, sinistro.getIdSinistro());
		stm.setString(2, sinistro.getDataOcorrencia());
		stm.setString(3, sinistro.getDescricaoSinistro());

		stm.executeUpdate();
	}

	public List<Sinistro> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = con5.prepareStatement("select * from sinistro");

		ResultSet result = stm.executeQuery();
		List<Sinistro> lista = new ArrayList<Sinistro>();

		while (result.next()) {
			Sinistro sinistro = parse(result);
			lista.add(sinistro);
		}

		return lista;
	}

	private Sinistro parse(ResultSet result) throws SQLException {

		int idSinistro = result.getInt("ID do sinistro");
		String dataOcorrencia = result.getString("Data da ocorrência");
		String descricaoSinistro = result.getString("descrição do sinistro");

		Sinistro sinistro = new Sinistro(idSinistro, dataOcorrencia, descricaoSinistro);

		return sinistro;
	}
	
	
	public void remover(int idSinistro) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = con5.prepareStatement("delete from sinistro where id_sinistro = ?");
		stm.setInt(1, idSinistro);
		
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Nome n�o encontrado para remo��o");
	}

	public void atualizar(Sinistro sinistro) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = con5.prepareStatement("update sinistro set desc_sinistro = ? where id_sinistro = ?");
		stm.setString(1, sinistro.getDescricaoSinistro());
		stm.setInt(2, sinistro.getIdSinistro());
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Nome n�o encontrado para atualizar");
	}


	
}
