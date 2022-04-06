package com.fatesg.ads4.projetoMirror.domain.postmon;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Adress {

	@JsonProperty("bairro")
	private String bairro;
	
	@JsonProperty("cidade")
	private String cidade;
	
	@JsonProperty("logradouro")
	private String logradouro;
	
	@JsonProperty("estado_info")
	private StateInfo estado_info;
	
	@JsonProperty("cep")
	private String cep;
	
	@JsonProperty("cidade_info")
	private CityInfo cidade_info;
	
	@JsonProperty("estado")
	private String estado;

	//GETTERS E SETTERS
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public StateInfo getEstado_info() {
		return estado_info;
	}

	public void setEstado_info(StateInfo estado_info) {
		this.estado_info = estado_info;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public CityInfo getCidade_info() {
		return cidade_info;
	}

	public void setCidade_info(CityInfo cidade_info) {
		this.cidade_info = cidade_info;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Adress [bairro=" + bairro + ", cidade=" + cidade + ", logradouro=" + logradouro + ", estado_info="
				+ estado_info + ", cep=" + cep + ", cidade_info=" + cidade_info + ", estado=" + estado + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cep, cidade, cidade_info, estado, estado_info, logradouro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adress other = (Adress) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(cidade, other.cidade) && Objects.equals(cidade_info, other.cidade_info)
				&& Objects.equals(estado, other.estado) && Objects.equals(estado_info, other.estado_info)
				&& Objects.equals(logradouro, other.logradouro);
	}

	
}
