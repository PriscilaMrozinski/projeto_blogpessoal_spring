package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Postagem;

//configura a herança: extends (trabalha com a tabela Postagem e usa atributo Long que seria o id
//define o metodo JpaRepository e depois o Hibernate sabe como fazer
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	

}
