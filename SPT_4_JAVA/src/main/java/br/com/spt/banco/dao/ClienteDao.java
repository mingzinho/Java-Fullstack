package br.com.spt.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.model.Cliente;

public class ClienteDao {

	private Connection con1;

	public ClienteDao(Connection con1) {
		this.con1 = con1;
	}
 
	public void cadastrar(Cliente cliente) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = con1.prepareStatement("INSERT INTO cliente (id_clie, nm_clie, dt_nasc, cpf_clie, end_clie) "
				+ "values (?,?,?,?,?)");
		
		stm.setInt(1, cliente.getIdCliente());
		stm.setString(2, cliente.getNomeCliente());
		stm.setString(3, cliente.getDataNascimento());
		stm.setString(4, cliente.getCpfCliente());
		stm.setString(5, cliente.getEnderecoCliente());

		stm.executeUpdate();
	}

	public List<Cliente> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = con1.prepareStatement("select * from cliente");

		ResultSet result = stm.executeQuery();
		List<Cliente> lista = new ArrayList<Cliente>();

		while (result.next()) {
			Cliente cliente = parse(result);
			lista.add(cliente);
		}

		return lista;
	}

	private Cliente parse(ResultSet result) throws SQLException {

		int idCliente = result.getInt("Id do cliente");
		String nomeCliente = result.getString("Nome do cliente");
		String dataNascimento = result.getString("Data de nascimento do cliente");
		String cpfCliente = result.getString("CPF do cliente");
		String enderecoCliente = result.getString("Endereço do cliente");

		Cliente cliente = new Cliente(idCliente, nomeCliente, dataNascimento, cpfCliente, enderecoCliente);

		return cliente;
	}
	
	
	public void remover(int idCliente) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = con1.prepareStatement("delete from cliente where id_clie = ?");
		stm.setInt(1, idCliente);
		
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Nome n�o encontrado para remo��o");
	}

	public void atualizar(Cliente cliente) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = con1.prepareStatement("update cliente set end_clie = ? where id_clie = ?");
		stm.setString(1, cliente.getEnderecoCliente());
		stm.setInt(2, cliente.getIdCliente());
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Nome n�o encontrado para atualizar");
	}


	
}
