package com.loja.BackLoja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.BackLoja.dto.TokenRefreshRequestDTO;
import com.loja.BackLoja.dto.TokenRefreshResponseDTO;
import com.loja.BackLoja.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/refresh")
	public ResponseEntity<TokenRefreshResponseDTO> refreshAccessToken(@RequestBody TokenRefreshRequestDTO request)
	{
		String refreshToken = request.getRefreshToken();
		
		if(jwtUtil.validarToken(refreshToken))
		{
			String email = jwtUtil.getEmailToken(refreshToken);
			String newAccessToken = jwtUtil.gerarTokenEmail(email);
			
			return ResponseEntity.ok(new TokenRefreshResponseDTO(newAccessToken, refreshToken));
		}
		else
		{
			return ResponseEntity.badRequest().build();
		}
	}
}
