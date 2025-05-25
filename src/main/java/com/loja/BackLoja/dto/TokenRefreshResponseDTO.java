package com.loja.BackLoja.dto;

public class TokenRefreshResponseDTO {

	private String accesToken;
	private String refreshToken;
	
	public TokenRefreshResponseDTO(String accessToken, String refreshToken)
	{
		this.accesToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public String getAccesToken() {
		return accesToken;
	}
	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
