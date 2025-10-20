package com.generation.blogpessoal.model;
 

//Classe auxiliar apenas para fazer a autenticação, não criará tabela nenhuma. 
// Verifica o login e senha na autenticação e não precisa armazenar os dados.
//DTO - Data Transfer 
public class UsuarioLogin {
	private Long id;
	private String nome;
	private String usuario;
	private String senha;
	private String foto;
	private String token;
 
	public Long getId() {
		return this.id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
 
	public String getNome() {
		return this.nome;
	}
 
	public void setNome(String nome) {
		this.nome = nome;
	}
 
	public String getUsuario() {
		return this.usuario;
	}
 
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
 
	public String getSenha() {
		return this.senha;
	}
 
	public void setSenha(String senha) {
		this.senha = senha;
	}
 
	public String getFoto() {
		return this.foto;
	}
 
	public void setFoto(String foto) {
		this.foto = foto;
	}
 
	public String getToken() {
		return this.token;
	}
 
	public void setToken(String token) {
		this.token = token;
	}
 
}