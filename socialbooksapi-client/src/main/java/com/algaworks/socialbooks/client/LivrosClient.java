package com.algaworks.socialbooks.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.algaworks.socialbooks.client.domain.Livro;


public class LivrosClient {
	private RestTemplate restT;
	private String URI_BASE;
	private String URN_BASE="/livros";
	private String credencial;
	public LivrosClient(String url,String usuario, String senha) {
		this.restT = new RestTemplate();
		this.URI_BASE=url.concat(this.URN_BASE);
		String credencialAux = usuario + ":" + senha;
		this.credencial = "Basic " + Base64.getEncoder()
		.encodeToString(credencialAux.getBytes());
	}
	public List<Livro> listar() {
		RequestEntity<Void> req = RequestEntity.get
				(URI.create(URI_BASE))
				.header("Authorization", credencial).build();
		
		ResponseEntity<Livro[]> response = restT.exchange(req, Livro[].class);
		return Arrays.asList(response.getBody());
	}
	
	public String salvar(Livro livro) {
		RequestEntity<Livro> req = RequestEntity.post(URI.create(URI_BASE))
				.header("Authorization", credencial).body(livro);
		ResponseEntity<Void> response = restT.exchange(req, Void.class);
		return response.getHeaders().getLocation().toString();
	}
	
	public Livro buscar(String uri) {
		
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri))
				.header("Authorization", credencial).build();
		
		ResponseEntity<Livro> response = restT.exchange(request, Livro.class);
		return response.getBody();
	}
}
