package com.algaworks.socialbooks.client.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class Comentario {
	
	private Long id;
	
	@JsonInclude(value=Include.NON_NULL)
	
	private String texto;
	@JsonInclude(value=Include.NON_NULL)
	private String usuario;
	
	@JsonInclude(value=Include.NON_NULL)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	
	private Livro livro;
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
}
