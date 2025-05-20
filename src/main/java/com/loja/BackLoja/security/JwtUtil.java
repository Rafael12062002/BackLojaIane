package com.loja.BackLoja.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.loja.BackLoja.entity.Pessoa;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {

	private String chaveSecreta = "uD83DybX91Q!7eGq63YjRzN*8vNmzPLWxTBk3Jzq5KyhWTkE7rW4Ud7KrHF9zrL4U";
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
	
	public String getEmailToken(String token)
	{
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getBody().getSubject();
	}
	
	public boolean validarToken(String token, HttpServletRequest request)
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
