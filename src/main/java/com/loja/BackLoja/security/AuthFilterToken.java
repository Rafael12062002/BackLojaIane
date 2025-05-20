package com.loja.BackLoja.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.loja.BackLoja.service.PessoaDetailService;
import org.springframework.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilterToken extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private PessoaDetailService pessoaDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
		String path = request.getRequestURI();
		if(path.startsWith("/api/pessoa-gerenciamento/login"))
		{
			filterChain.doFilter(request, response);
			return;
		}
	    try {
	        String jwt = getToken(request);
	        if (jwt != null && jwtUtil.validarToken(jwt, request)) {
	            String email = jwtUtil.getEmailToken(jwt);
	            UserDetails userDetails = pessoaDetailService.loadUserByUsername(email);
	            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	            SecurityContextHolder.getContext().setAuthentication(auth);
	        }
	    } catch (Exception e) {
	        System.out.println("Não funcionou: " + e.getMessage());
	    }
	    
	    filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request)
	{
		String headerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer "))
		{
			return headerToken.replace("Bearer ", "");
		}
		return null;
	}
}
