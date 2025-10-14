package com.generation.blogpessoal.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo); //Query Methods
	// SELECT *FROM tb_postagens WHERE titulo LIKE "%?%";
}

//Extend: é a herança do JPA, que internamente já esta declarando o crud - configura a herança: extends (trabalha com a tabela Postagem e usa atributo Long que seria o id
//define o metodo JpaRepository e depois o Hibernate sabe como fazer

//Interface que representa o repositório de Postagem.
//Estends JpaRepository para herdar métodos prontos de CRUD (create, read, update, delete) e funcionalidades de paginação e ordenação.
//O primeiro parâmetro (Postagem) indica a entidade que este repositório gerencia.
//O segundo parâmetro (Long) indica o tipo do ID da entidade.