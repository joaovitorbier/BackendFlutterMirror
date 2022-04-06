package com.fatesg.ads4.projetoMirror.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatesg.ads4.projetoMirror.DTO.CredenciaisDTO;
import com.fatesg.ads4.projetoMirror.domain.Pessoa;
import com.fatesg.ads4.projetoMirror.repositories.PessoaRepository;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Autowired
	PessoaRepository service;
	
	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException{
		
		try {
			CredenciaisDTO creds = new ObjectMapper()
	                .readValue(req.getInputStream(), CredenciaisDTO.class);
	
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
	        
	        Authentication auth = authenticationManager.authenticate(authToken);
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException{
		
		//ORIGINAL QUE FUNCIONA
		String username = ((UserSS) auth.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("access-control-expose-headers", "Authorization");
        String responseToClient = "{\"token\": \"" + token + "\" }";
        res.getWriter().write(responseToClient);
        res.getWriter().flush();

		
		//VERSÃO DO JOÃO
		/*
		String username = ((UserSS) auth.getPrincipal()).getUsername();
		
		Pessoa pessoa = service.findByEmail(username);
		
		Object[] myArr = pessoa.getPerfils().toArray();
		String value1 = myArr[0].toString();
		//String value2 = myArr[1].toString();
		
        String token = jwtUtil.generateToken(username);
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("access-control-expose-headers", "Authorization");
        String responseToClient = "{\"token\": \"" + token + "\" ,  \"email\": \"" + username + "\"}";
        String userReturn = "{\"token\": " + token + ", "
                + "\"email\": \"" + username + "\", "
                + "\"id\": \"" + pessoa.getId() +"\", "
                + "\"perfil\": \""+value1+"\"}";
        res.getWriter().write(userReturn);
        res.getWriter().flush();
		*/
		
		//MINHA TENTATIVA DNV
		/*
		String username = ((UserSS) auth.getPrincipal()).getUsername();
		
		Pessoa pessoa = service.findByEmail(username);
		String userID = "" + pessoa.getId();
		
        String token = jwtUtil.generateToken(username);
        res.addHeader("user", userID);
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("access-control-expose-headers", "Authorization");
        res.addHeader("access-control-expose-headers", "user");
		*/
	}
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
            
            
		
	}
        
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }

	}
}