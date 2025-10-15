package com.generation.blogpessoal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity //informa que Tema é uma Entidade (Tabela)
@Table(name = "tb_temas") //define o nome da tabela
public class Tema {
	@Id //indentifica como chave-primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //define auto-increment
	private Long id;
	
	@Column(length=255) //define que o tamanho da coluna será de 100 caracteres
	@NotBlank(message = "O atributo descrição é obrigatório!") //Anotação de validação: campo não pode ser nulo e define o texto de erro, caso falhe a validação
	@Size(min=5, max=255, message="O atributo descrição deve conter no mínimo 05 e no máximo 255 caracteres")
	private String descricao;
	
	//Incluindo relacionamento com a Classe-Tabela Tema//Incluindo relacionamento com a Classe-Tabela Postagem:
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tema", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties(value = "tema", allowSetters = true) //Apenas a leitura get será ignorada, mas não ignorar os setters (que modifica)
	private List<Postagem> postagem; //lembra de colocar o mesmo nome postagem no arquivo Postagem do Model
	
	
	
	// NÃO precisa adicionar Método CONSTRUTOR
	
	// Adiciona GET e SET:
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
	
}

//FetchType: carregamento preguiçoso, só leva os dados quando pede os dados
//mappeBY: indica a chave estrangeira
//cascade: indica o que acontecerá com os filhos, exemplo se na mae excluir, excluir os filho tambem