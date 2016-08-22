package com.algaworks.socialbooks.resources;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.services.AutoresServices;

@RestController
@RequestMapping("/autores")
public class AutoresResource {

	@Autowired
	private AutoresServices autoresservices;
	
	@RequestMapping(method = RequestMethod.GET, produces={
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	private ResponseEntity<List<Autor>> ListaAutores() {
		return  ResponseEntity.status(HttpStatus.OK).body(autoresservices.ListarAutores());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor) {
		autor = this.autoresservices.salvar(autor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Autor> buscar(@PathVariable("id") Long id ) {
		return ResponseEntity.status(HttpStatus.OK).body(this.autoresservices.buscar(id));
		
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarporid(@PathVariable("id") Long id) {
		this.autoresservices.deleta_autoresporid(id);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar() {
		this.autoresservices.deleta();
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.PUT)
	public ResponseEntity<Autor> atualizar(@PathVariable("id") Long id, @RequestBody Autor autor)  {
		autor.setId(id);
		this.autoresservices.atualizar(autor);
		return ResponseEntity.status(HttpStatus.OK).body(autor);
	}
}
