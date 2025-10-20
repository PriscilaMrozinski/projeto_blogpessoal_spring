package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//Primeiro cria as classes: id, titulo, texto, etc
//Definir as regras de validação de dados, acrescenta os @:

@Entity // Diz ao JPA que essa classe é uma entidade (uma tabela do BD)
@Table(name = "tb_postagens") // Define o nome exato da tabela, seria o: CREATE TABLE tb_postagens()
public class Postagem {

	@Id // Indica que o atributo ID é a chave primária (PRIMARY KEY) da tabela.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT -- Atenção! O parametro pode manter o mesmo
														// para qq recurso, mas existem outros
	private Long id;

	@Column(length = 100) // define o tamanho que terá lá no BD
	@NotBlank(message = "O atributo título é obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 05 e no máximo 100 caracteres")
	private String titulo;

	@Column(length = 100)
	@NotBlank(message = "O atributo texto é obrigatório!")
	@Size(min = 10, max = 1000, message = "O atributo texto deve conter no mínimo 10 e no máximo 1000 caracteres")
	private String texto;

	@UpdateTimestamp // atualiza data e hora quando cria e atualiza
	private LocalDateTime data; // guarda data e hora

	// Incluindo relacionamento com a Classe-Tabela Tema
	@ManyToOne
	@JsonIgnoreProperties("postagem") // quebra o loop infinito -- mesmo nome no arquivo Tema da model
	private Tema tema;

	// Incluindo relacionamento com a Classe-Tabela Usuário
	@ManyToOne
	@JsonIgnoreProperties("postagem") // quebra o loop infinito -- mesmo nome no arquivo Tema da model
	private Usuario usuario;

	// no Springboot não precisa adicionar o método construtor

	// Adiciona os métodos Get e Set:

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
