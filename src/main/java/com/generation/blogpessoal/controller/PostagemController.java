package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;


@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders ="*") //libera a origem e cabeçalhos, o asterisco *: quer dizer liberar todos
public class PostagemController {
	
	//Criar a injeção de dependencia:
	@Autowired
	private PostagemRepository postagemRepository;
	
	//Configurando a Resposta HTPP, quer retornar uma lista de postagens
	//getAll: para pegar tudo
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll()); //findAll quer dizer: SELECT * FROM tb_postagens;
		
	}
	

}
