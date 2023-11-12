package br.com.spt.banco.model;

public class Pagamento {

	private int idPagamento;
	private double valorPagamento;
	private String dataPagamento;
	private String formaPagamento;
	private int quantidadeParcelas;
	
	
	public Pagamento() {
		
	}
	
	public Pagamento(int idPagamento, double valorPagamento, String dataPagamento, String formaPagamento,
			int quantidadeParcelas) {
		setIdPagamento(idPagamento);
		setValorPagamento(valorPagamento);
		setDataPagamento(dataPagamento);
		setFormaPagamento(formaPagamento);
		setQuantidadeParcelas(quantidadeParcelas);
	}

	
	public int getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}
	public double getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	
	
}
