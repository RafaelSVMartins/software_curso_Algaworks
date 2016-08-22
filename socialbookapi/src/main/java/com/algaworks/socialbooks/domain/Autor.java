package com.algaworks.socialbooks.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Autor {

	public Autor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@javax.persistence.Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long Id;
	@NotEmpty(message="O campo não pode ser nulo!")
	private String nome;
	@JsonInclude(value=Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date datanascimento;
	@JsonInclude(value=Include.NON_NULL)
	private String nascionalidade;

	@OneToMany(mappedBy="autor")
	@JsonIgnore
	private List<Livro> livrosautores;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDatanascimento() {
		return datanascimento;
	}
	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}
	public String getNascionalidade() {
		return nascionalidade;
	}
	public void setNascionalidade(String nascionalidade) {
		this.nascionalidade = nascionalidade;
	}
	public List<Livro> getLivrosautores() {
		return livrosautores;
	}
	public void setLivrosautores(List<Livro> livrosautores) {
		this.livrosautores = livrosautores;
	}
	
	
}
