package br.com.spt.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.model.Bike;

public class BikeDao {
	
	private Connection con2;

	public BikeDao(Connection con2) {
		this.con2 = con2;
	}

	public void cadastrar(Bike bike) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = con2.prepareStatement("INSERT INTO bike (id_bike, bike_modelo, ano_fabricacao, numero_serie, bike_cor"
				+ "bike_marca, bike_valor) values (?,?,?,?,?,?,?)");
		
		stm.setInt(1, bike.getIdBike());
		stm.setString(2, bike.getModeloBike());
		stm.setString(3, bike.getAnoFabricacao());
		stm.setString(4, bike.getNumeroDeSerie());
		stm.setString(5, bike.getCorBike());
		stm.setString(6, bike.getMarcaBike());
		stm.setDouble(7, bike.getValorBike());

		stm.executeUpdate();
	}

	public List<Bike> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = con2.prepareStatement("select * from cliente, bike");

		ResultSet result = stm.executeQuery();
		List<Bike> lista = new ArrayList<Bike>();

		while (result.next()) {
			Bike bike = parse(result);
			lista.add(bike);
		}

		return lista;
	}

	private Bike parse(ResultSet result) throws SQLException {

		int idCliente = result.getInt("Id do cliente");
		String nomeCliente = result.getString("Nome do cliente");
		String dataNascimento = result.getString("Data de nascimento do cliente");
		String cpfCliente = result.getString("CPF do cliente");
		String enderecoCliente = result.getString("Endereco do cliente");
		
		int idBike = result.getInt("ID da bike");
		String modeloBike = result.getString("Modelo da bike");
		String anoFabricacao = result.getString("Ano da fabricação da bike");
		String numeroDeSerie = result.getString("Chassi da bike");
		String corBike = result.getString("Cor da bike");
		String marcaBike = result.getString("Marca da bike");
		double valorBike = result.getDouble("Valor da Bike");

		Bike bike = new Bike(idCliente, nomeCliente, dataNascimento, cpfCliente, enderecoCliente,
				idBike, modeloBike, anoFabricacao, numeroDeSerie, corBike, marcaBike, valorBike);

		return bike;
	}
	
	
	public void remover(int idBike) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = con2.prepareStatement("delete from bike where id_bike = ?");
		stm.setInt(1, idBike);
		
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Nome n�o encontrado para remo��o");
	}

	public void atualizar(Bike bike) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = con2.prepareStatement("update bike set bike_valor = ? where id_bike = ?");
		stm.setDouble(1, bike.getValorBike());
		stm.setInt(2, bike.getIdBike());
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Nome n�o encontrado para atualizar");
	}



}
