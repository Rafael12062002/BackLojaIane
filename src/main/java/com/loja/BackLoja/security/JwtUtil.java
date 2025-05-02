package com.loja.BackLoja.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.loja.BackLoja.entity.Pessoa;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private String chaveSecreta = "sua-chave-super-secreta-de-256-bits";
	private int validadeToken = 900000;
	SecretKey key = Keys.hmacShaKeyFor(chaveSecreta.getBytes());
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	public String gerarTokenUserName(Pessoa pessoa)
	{
		return Jwts.builder()
				.subject(pessoa.getUsername())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + validadeToken))
				.signWith(key, Jwts.SIG.HS512)
				.compact();
	}
	
	public boolean validarToken(String token)
	{
		try
		{
			Jwts.parser()
		    .verifyWith(key)
		    .build()
		    .parseSignedClaims(token)
		    .getPayload();
			return true;
		}
		catch(Exception e)
		{
			logger.error("Assinatura invalida", e.getMessage());
		}
		return false;
	}
}
