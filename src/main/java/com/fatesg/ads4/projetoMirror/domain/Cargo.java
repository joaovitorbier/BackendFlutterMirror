package com.fatesg.ads4.projetoMirror.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Cargo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	
	@OneToMany
	@JoinTable
	private List<Pessoa> pessoas;
	
	//Construtores
	public Cargo(String descricao) {
		super();
		this.id = null;
		this.descricao = descricao;
	}
	
	public Cargo() {
		super();
	}
	
	//Getters e Setters
	public Integer getId() {
		return id;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(descricao, id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cargo other = (Cargo) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Cargo [id=" + id + ", descricao=" + descricao + ", pessoas=" + pessoas + "]";
	}
}