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

import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;

@RestController //define como controlador
@RequestMapping("/temas") // define endereço
@CrossOrigin(origins = "*", allowedHeaders ="*") //libera qualquer requisição
public class TemaController {
	
	@Autowired //Pede ao Spring para injetar automaticamente um objeto gerenciado, sem precisar instanciar (new etc)
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(temaRepository.findAll()); //findAll quer dizer: SELECT * FROM tb_temas;
	
	}
	
	@GetMapping("/{id}") //Método para buscar um tema pelo ID passado na URL
	public ResponseEntity<Tema> getById(@PathVariable Long id){
		return temaRepository.findById(id) 
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}	
	
	//Criando Query Methods: métodos de consulta personalizados
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getAllByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	} //Atenção aqui!!! descricao com d ou D maiusculo.
	
		
	@PostMapping //Criando o Método Post, cria um novo tema
	public ResponseEntity<Tema> post(@Valid @RequestBody Tema tema){
		tema.setId(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
	}
	
	
	@PutMapping //Cria um Update - uma atualização
	public ResponseEntity<Tema> put(@Valid @RequestBody Tema tema){
		return temaRepository.findById(tema.getId()) 
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//Cria um Delete
	@ResponseStatus(HttpStatus.NO_CONTENT) //no_content: quer dizer nao existe mais o conteúdo
	@DeleteMapping ("/{id}") // {id} : é a variavel de caminho
	public void delete(@PathVariable Long id) {
		
		//Verificar se o tema existe:
		Optional<Tema> tema = temaRepository.findById(id);
		if(tema.isEmpty()) //verifica se optional está vazio, se vazio não encontrou retorna 404
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		temaRepository.deleteById(id); //se passou pela verificação, ou seja, encontrou o tema, deleta e retorna 204, deu ok o delete
	}

	
	
}






//Explicação das Anotações:

// @RestController: Diz ao Spring Boot que essa classe é um controlador REST.
// Significa que a Classe "Tema" receberá as requisições HTTP, como GET, POST, PUT, DELETE


// @RequestMapping("/temas"): Define o endereço base (endpoint) que será usado para acessar esse controlador.
// exemplo, todas as rotas dessa classe começam com /temas.


// @CrossOrigin(origins = "*", allowedHeaders ="*"): 
// é o Libera Geral! ATENÇÃO a segurança! Sites maliciosos podem fazer requisições ao seu backend e explorar vulnerabilidades.
// serve para liberar o acesso à API de outras origens (outros domínios ou aplicações).
// Isso é necessário por causa do CORS (Cross-Origin Resource Sharing), mecanismo segurança que impede q um site acesse outro servidor sem permissão.

// @Autowired: serve para o Spring injetar automaticamente uma dependência em um componente (classe, controller, serviço, etc.).
//Faz o Spring criar e fornecer automaticamente o objeto que você precisa, sem que você precise instanciar com new.




