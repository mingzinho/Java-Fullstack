package br.com.spt.banco.model;

public class Sinistro {
	
	private int idSinistro;
	private String dataOcorrencia;
	private String descricaoSinistro;
	
	
	public Sinistro() {
		
	}
	
	public Sinistro(int idSinistro, String dataOcorrencia, String descricaoSinistro) {
		setIdSinistro(idSinistro);
		setDataOcorrencia(dataOcorrencia);
		setDescricaoSinistro(descricaoSinistro);
	}


	public int getIdSinistro() {
		return idSinistro;
	}
	public void setIdSinistro(int idSinistro) {
		this.idSinistro = idSinistro;
	}
	public String getDataOcorrencia() {
		return dataOcorrencia;
	}
	public void setDataOcorrencia(String dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	public String getDescricaoSinistro() {
		return descricaoSinistro;
	}
	public void setDescricaoSinistro(String descricaoSinistro) {
		this.descricaoSinistro = descricaoSinistro;
	}
	

}
