package com.algaworks.socialbooks.client.domain;

import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class Autor {

	public Autor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	private Long Id;
	
	private String nome;
	@JsonInclude(value=Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date datanascimento;
	@JsonInclude(value=Include.NON_NULL)
	private String nascionalidade;

	
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
