package com.fatesg.ads4.projetoMirror.domain.postmon;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityInfo {

	@JsonProperty("area_km2")
	private String area;
	
	@JsonProperty("codigo_ibge")
	private String codeIBGE;

	//GETTERS E SETTERS
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCodeIBGE() {
		return codeIBGE;
	}

	public void setCodeIBGE(String codeIBGE) {
		this.codeIBGE = codeIBGE;
	}

	@Override
	public String toString() {
		return "CityInfo [area=" + area + ", codeIBGE=" + codeIBGE + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(area, codeIBGE);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityInfo other = (CityInfo) obj;
		return Objects.equals(area, other.area) && Objects.equals(codeIBGE, other.codeIBGE);
	}
	
	
	
}
