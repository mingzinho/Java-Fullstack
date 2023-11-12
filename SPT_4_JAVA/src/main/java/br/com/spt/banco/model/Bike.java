package br.com.spt.banco.model;

public class Bike extends Cliente{
	
	private int idBike;
	private String modeloBike;
	private String anoFabricacao;
	private String numeroDeSerie;
	private String corBike;
	private String marcaBike;
	private double valorBike;
	
	
	public Bike() {
		
	}
	
	public Bike(int recebeIdCliente, String recebeNomeCliente, String recebeDataNascimento, String recebeCpfCliente,
			String recebeEnderecoCliente, int recebeIdBike, String recebeModeloBike, String recebeAnoFabricacao, String recebeNumeroDeSerie,
			String recebeCorBike, String recebeMarcaBike, double recebeValorBike) {
		super(recebeIdCliente, recebeNomeCliente, recebeDataNascimento, recebeCpfCliente, recebeEnderecoCliente);
		setIdBike(recebeIdBike);
		setModeloBike(recebeModeloBike);
		setAnoFabricacao(recebeAnoFabricacao);
		setNumeroDeSerie(recebeNumeroDeSerie);
		setCorBike(recebeCorBike);
		setMarcaBike(recebeMarcaBike);
		setValorBike(recebeValorBike);
	}
	
	
	public int getIdBike() {
		return idBike;
	}
	public void setIdBike(int idBike) {
		this.idBike = idBike;
	}
	public String getModeloBike() {
		return modeloBike;
	}
	public void setModeloBike(String modeloBike) {
		this.modeloBike = modeloBike;
	}
	public String getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	public String getCorBike() {
		return corBike;
	}
	public void setCorBike(String corBike) {
		this.corBike = corBike;
	}
	public String getMarcaBike() {
		return marcaBike;
	}
	public void setMarcaBike(String marcaBike) {
		this.marcaBike = marcaBike;
	}
	public double getValorBike() {
		return valorBike;
	}
	public void setValorBike(double valorBike) {
		this.valorBike = valorBike;
	}
	

}
