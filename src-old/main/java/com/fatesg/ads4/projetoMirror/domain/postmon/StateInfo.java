package com.fatesg.ads4.projetoMirror.domain.postmon;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StateInfo {

	@JsonProperty("area_km2")
	private String area;
	
	@JsonProperty("codigo_ibge")
	private String codeIBGE;
	
	@JsonProperty("nome")
	private String name;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "StateInfo [area=" + area + ", codeIBGE=" + codeIBGE + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(area, codeIBGE, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateInfo other = (StateInfo) obj;
		return Objects.equals(area, other.area) && Objects.equals(codeIBGE, other.codeIBGE)
				&& Objects.equals(name, other.name);
	}
	
	
}
