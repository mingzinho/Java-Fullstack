package br.com.spt.banco.model;

public class Cliente {
	
	private int idCliente;
	private String nomeCliente;
	private String dataNascimento;
	private String cpfCliente;
	private String enderecoCliente;
	
	
	public Cliente() {
		
	}
	
	public Cliente(int recebeIdCliente, String recebeNomeCliente, String recebeDataNascimento, String recebeCpfCliente,
			String recebeEnderecoCliente) {
		setIdCliente(recebeIdCliente);
		setNomeCliente(recebeNomeCliente);
		setDataNascimento(recebeDataNascimento);
		setCpfCliente(recebeCpfCliente);
		setEnderecoCliente(recebeEnderecoCliente);
	}

	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public String getEnderecoCliente() {
		return enderecoCliente;
	}
	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}
	

}
