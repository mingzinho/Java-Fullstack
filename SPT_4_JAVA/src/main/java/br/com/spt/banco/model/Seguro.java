package br.com.spt.banco.model;

public class Seguro extends Pagamento{
	
	private int idSeguro;
	private String tipoSeguro;
	private String dataInicio;
	private String dataTermino;
	
	
	public Seguro() {
		
	}
	
	public Seguro(int recebeIdPagamento, double recebeValorPagamento, String recebeDataPagamento, String recebeFormaPagamento,
			int recebeQuantidadeParcelas, int recebeIdSeguro, String recebeTipoSeguro, String recebeDataInicio, String RecebeDataTermino) {
		super(recebeIdPagamento, recebeValorPagamento, recebeDataPagamento, recebeFormaPagamento, recebeQuantidadeParcelas);
		setIdSeguro(recebeIdSeguro);
		setTipoSeguro(recebeTipoSeguro);
		setDataInicio(recebeDataInicio);
		setDataTermino(RecebeDataTermino);
	}


	public int getIdSeguro() {
		return idSeguro;
	}
	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}
	public String getTipoSeguro() {
		return tipoSeguro;
	}
	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}
	

}
