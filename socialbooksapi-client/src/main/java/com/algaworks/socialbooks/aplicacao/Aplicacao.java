package com.algaworks.socialbooks.aplicacao;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.algaworks.socialbooks.client.LivrosClient;
import com.algaworks.socialbooks.client.domain.Livro;

public class Aplicacao {
	public static void main(String[] args) {
		LivrosClient cliente = new LivrosClient("http://localhost:8080","root","1234");
		List<Livro> listalivros = cliente.listar();
		
		for(Livro livro : listalivros) {
			System.out.println("Livro: " + livro.getNome());
		}
		
		Livro l = new Livro();
		l.setNome("Rest Java");
		l.setEditora("Érica");
		
		SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy");
		try {
			l.setPublicacao(publicacao.parse("01/05/2016"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		l.setResumo("O livro desenvolve através de REST!");
		
		String localizacao = cliente.salvar(l);
		System.out.println("localização do livro salvo" + localizacao);
		
		Livro li = cliente.buscar(localizacao);
		
		System.out.println("nome do livro buscado: " + li.getNome());
	}
}
