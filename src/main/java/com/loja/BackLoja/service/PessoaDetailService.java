package com.loja.BackLoja.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loja.BackLoja.entity.Pessoa;
import com.loja.BackLoja.repository.PessoaRepository;

@Service
public class PessoaDetailService implements UserDetailsService{

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Pessoa pessoa = pessoaRepository.findByEmail(username);
		if(pessoa == null)
		{
			throw new UsernameNotFoundException("Usuario não encontrado pelo email");
		}
		
		return pessoa;
	}

}
