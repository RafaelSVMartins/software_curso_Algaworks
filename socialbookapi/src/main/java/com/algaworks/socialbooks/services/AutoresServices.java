package com.algaworks.socialbooks.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.algaworks.services.exceptions.AutorExistenteException;
import com.algaworks.services.exceptions.AutorNaoEncontrado;
import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutoresRepository;

@Service
public class AutoresServices {

	@Autowired
	private AutoresRepository autoresrepository;
	
	public List<Autor> ListarAutores() {
		return this.autoresrepository.findAll();
	}
	
	public void atualizar(Autor autor) {
		this.buscar(autor.getId());
		this.autoresrepository.save(autor); 
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Autor a = this.autoresrepository.findOne(autor.getId());
			if(a != null) {
				throw new AutorExistenteException("O autor já existe!");
			}
		}
		return this.autoresrepository.save(autor);
	}
	
	public Autor buscar(Long Id) {
		Autor autor = this.autoresrepository.findOne(Id);
		if(autor == null) {
			throw new AutorNaoEncontrado("O autor não foi encontrado!");
		}
		return autor;
	}
	
	public void deleta_autoresporid(Long id) {
		Autor autor = this.buscar(id);
		 this.autoresrepository.delete(autor);
	}

	public void deleta() {
		// TODO Auto-generated method stub
		List<Autor> autores = new ArrayList();
		autores.addAll(this.autoresrepository.findAll());
		this.autoresrepository.delete(autores);
	}
}
