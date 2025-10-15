package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders ="*") //libera a origem e cabeçalhos, o asterisco *: quer dizer liberar todos
public class PostagemController {
	
	//Criar a injeção de dependencia:
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	//Configurando a Resposta HTPP, quer retornar uma lista de postagens
	//getAll: para pegar tudo
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll()); //findAll quer dizer: SELECT * FROM tb_postagens;
	
	}
	//Método para buscar uma postagem pelo ID passado na URL
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id) 
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	// findById: SELECT *FROM tb_postagens WHERE id = (aqui a variavel);
	
	
	//Criando Query Methods: métodos de consulta personalizados
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getAllByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	//Lembrando que Anotação é P maiusculo, de Postagem

	
	//Criando o Método Post:
	//Cria uma nova postagem: 
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
		if(temaRepository.existsById(postagem.getTema().getId())) {
			postagem.setId(null);
			return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Tema não existe!", null);
	}
	
	//Cria um Update - uma atualização:
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		if(postagemRepository.existsById(postagem.getId())) {
			if(temaRepository.existsById(postagem.getTema().getId())) {
				return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Tema não existe!", null);
		}
		return ResponseEntity.notFound().build();
	}
	
	//Cria um Delete
	@ResponseStatus(HttpStatus.NO_CONTENT) //no_content: quer dizer nao existe mais o conteúdo
	@DeleteMapping ("/{id}") // {id} : é a variavel de caminho
	public void delete(@PathVariable Long id) {
		
		//Verificar se a postagem existe:
		Optional<Postagem> postagem = postagemRepository.findById(id);
		if(postagem.isEmpty()) //verifica se optional está vazio, se vazio não encontrou retorna 404
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		postagemRepository.deleteById(id); //se passou pela verificação, ou seja, encontrou a postagem, deleta e retorna 204, deu ok o delete
	}
	
}
