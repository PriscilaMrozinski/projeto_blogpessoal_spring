package com.generation.blogpessoal.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.generation.blogpessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L; //Serialização e Deserialização: transforma um objeto Java em Json, e vice versa -- identifica a versao da classe do objeto

	private String username;
	private String password;
	

	public UserDetailsImpl(Usuario user) {
		this.username = user.getUsuario();
		this.password = user.getSenha();
	}
	
	//Direitos de acesso:
	
	// Pegar as autorizações:
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return Collections.emptyList();  
	}

	// Retornar senha:
	@Override
	public String getPassword() {

		return password;
	}

	// Retorna nome:
	@Override
	public String getUsername() {

		return username;
	}

	// Restrições de contas:
	
	// Métodos de autenticação, retorna verdadeiro tudo:
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}