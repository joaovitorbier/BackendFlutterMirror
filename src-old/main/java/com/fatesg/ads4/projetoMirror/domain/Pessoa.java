package com.fatesg.ads4.projetoMirror.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatesg.ads4.projetoMirror.enumeradores.Perfil;
import com.fatesg.ads4.projetoMirror.enumeradores.Status;
import com.fatesg.ads4.projetoMirror.enumeradores.Tipo;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@Column(unique=true)
	private String email;
	
	private String cpfCnpj;
	private String telefone;
	private String anotacao;
	
	private String senha; //SENHA DO LOGIN
	
	//DATAS
	private Date dataNascimento;
	private Date dataCadastro;
	private Date dataBloqueio;
	private Date dataExclusao;
	private Date dataInativacao;
	
	//ENUMS
	private Status status; // ATIVO, INATIVO E BLOQUEADO
	private Tipo tipo; //PESSOA FISICA E PESSOA JURIDICA
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet();
	
	@ManyToMany
	@JoinTable
	private List<Feedback> feedbacks;
	
	@ManyToOne
	@JoinColumn
	private Endereco endereco; //USAR O VIA CEP ESSA SEMANA
	
	private Departamento departamento;
	
	private Cargo cargo;

	private Unidade unidade;
	
	//CONSTRUTORES
	public Pessoa() {
		super();
		addPerfil(Perfil.CLIENTE);

	}
	
	//ESTE CONSTRUTOR FOI SÓ PARA TESTAR A CRIPTOGRAFIA, DEIXAR AQUI POR SÓ PRA TESTAR
	public Pessoa(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
		//addPerfil(Perfil.CLIENTE);
		Date agora = new Date();
		this.dataCadastro = agora;
		setStatus(Status.ATIVO);

	}

	public Pessoa(String nome, String email, String senha, String cpfCnpj, String telefone, String anotacao,Date dataNascimento,Endereco endereco, Departamento departamento,Cargo cargo,Unidade unidade) {
		super();
		
		this.id = null;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.anotacao = anotacao;
		this.dataNascimento = dataNascimento;
		
		this.dataBloqueio = null;
		this.dataExclusao = null;
		this.dataInativacao = null;
		
		this.endereco = endereco;
		this.departamento = departamento;
		this.cargo = cargo;
		this.unidade = unidade;
		
		//Set básico do sistema, todo usuário é automaticamente setado como cliente e a hora de cadastro é a hora do sistema
		Date agora = new Date();
		this.dataCadastro = agora;
		addPerfil(Perfil.CLIENTE);
		setStatus(Status.ATIVO);
		
		//DECIDE O TIPO PELO NÚMERO DE CARACTERES EM cpfCpnj
		if(cpfCnpj.length() == 11) {
			setTipo(tipo.FISICA);
		}else setTipo(tipo.JURIDICA);
		
	}

	//GETTERS E SETTERS
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<Perfil> getPerfils(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
		
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
		
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataBloqueio() {
		return dataBloqueio;
	}

	public void setDataBloqueio(Date dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Date getDataInativacao() {
		return dataInativacao;
	}

	public void setDataInativacao(Date dataInativacao) {
		this.dataInativacao = dataInativacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(anotacao, cargo, cpfCnpj, dataBloqueio, dataCadastro, dataExclusao, dataInativacao,
				dataNascimento, departamento, email, endereco, feedbacks, id, nome, perfis, senha, status, telefone,
				tipo, unidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(anotacao, other.anotacao) && Objects.equals(cargo, other.cargo)
				&& Objects.equals(cpfCnpj, other.cpfCnpj) && Objects.equals(dataBloqueio, other.dataBloqueio)
				&& Objects.equals(dataCadastro, other.dataCadastro) && Objects.equals(dataExclusao, other.dataExclusao)
				&& Objects.equals(dataInativacao, other.dataInativacao)
				&& Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(departamento, other.departamento) && Objects.equals(email, other.email)
				&& Objects.equals(endereco, other.endereco) && Objects.equals(feedbacks, other.feedbacks)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(perfis, other.perfis) && Objects.equals(senha, other.senha) && status == other.status
				&& Objects.equals(telefone, other.telefone) && tipo == other.tipo
				&& Objects.equals(unidade, other.unidade);
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + ", cpfCnpj=" + cpfCnpj + ", telefone="
				+ telefone + ", anotacao=" + anotacao + ", senha=" + senha + ", dataNascimento=" + dataNascimento
				+ ", dataCadastro=" + dataCadastro + ", dataBloqueio=" + dataBloqueio + ", dataExclusao=" + dataExclusao
				+ ", dataInativacao=" + dataInativacao + ", status=" + status + ", tipo=" + tipo + ", perfis=" + perfis
				+ ", feedbacks=" + feedbacks + ", endereco=" + endereco + ", departamento=" + departamento + ", cargo="
				+ cargo + ", unidade=" + unidade + "]";
	}

	
}