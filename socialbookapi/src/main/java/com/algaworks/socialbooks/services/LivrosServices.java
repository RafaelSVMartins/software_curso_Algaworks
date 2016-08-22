package com.algaworks.socialbooks.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.services.exceptions.LivroNaoEncontradoException;
import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.repository.LivrosRepository;

@Service
public class LivrosServices {

		@Autowired
		private LivrosRepository livrosrepository;
		@Autowired
		private ComentariosRepository comentariorepository;
		
		public List<Livro> listar() {
			return this.livrosrepository.findAll();
		}
		
		public Livro buscar(Long id) {
			Livro livro = this.livrosrepository.findOne(id);
			
			if(livro == null) {
				throw new LivroNaoEncontradoException("O livro não foi encontrado!");
			}
			return livro;
		}
		
		public Livro salvar(Livro livro) {
			livro.setId(null);
			return this.livrosrepository.save(livro);
		}
		
		public void deletar(Long id) {
			try {
				this.livrosrepository.delete(id);
			} catch (LivroNaoEncontradoException e) {
				// TODO: handle exception
				throw new LivroNaoEncontradoException("O livro não foi encontrado!");
			}
		}
		
		public void atualizar(Livro livro) {
			this.verificarexistencia(livro);
			try {
				this.livrosrepository.save(livro);
			} catch(DataIntegrityViolationException e) {
				throw new DataIntegrityViolationException("Requisição inválida");
			} 
		}
		
		public void verificarexistencia(Livro livro) {
			buscar(livro.getId());
		}
		
		public Comentario salvarComentario(Long LivroId, Comentario comentario) {
			Livro l = buscar(LivroId);
			comentario.setLivro(l);
			comentario.setData(new Date());
			
			return comentariorepository.save(comentario);
		}
		
		public List<Comentario> listarComentario(Long Id) {
			Livro l = buscar(Id);
			return l.getComentarios();
		}
		
		
		public void deletarcomentarios(Long id) {
			Livro l = buscar(id);
			for (Comentario c : l.getComentarios()) {
				comentariorepository.delete(c);
			}
		}
}
