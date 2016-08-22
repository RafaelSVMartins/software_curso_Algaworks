package com.algaworks.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.services.LivrosServices;



@RestController
@RequestMapping("/livros")
public class LivrosResources {

	@Autowired
	private LivrosServices livrosSercvices;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(this.livrosSercvices.listar());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
		this.livrosSercvices.salvar(livro);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Livro l = this.livrosSercvices.buscar(id);
		CacheControl cache = CacheControl.maxAge(20, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(l);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		this.livrosSercvices.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		this.livrosSercvices.atualizar(livro);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> AdicionarComentarios(@PathVariable("id") Long id, @RequestBody Comentario comentario) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		comentario.setUsuario(auth.getName());
		livrosSercvices.salvarComentario(id, comentario);
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping( value="/{id}/comentarios", method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentario(
			@PathVariable("id") Long LivroId) {
		List<Comentario> comentarios = this.livrosSercvices.listarComentario(LivroId);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
	
	@RequestMapping(value="/{id}/comentarios", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarComentario(@PathVariable("id") Long Comentarioid) {
		livrosSercvices.deletarcomentarios(Comentarioid);
		return ResponseEntity.noContent().build();
	}
}


