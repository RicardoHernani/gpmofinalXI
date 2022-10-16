package com.ricardochaves.services;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ricardochaves.domain.Usuario;
import com.ricardochaves.enums.Perfil;
import com.ricardochaves.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public void instantiateTestDatabase() {
			
		Usuario usu1 = new Usuario(null, "Ricardo", "rhac.sistemas@gmail.com", pe.encode("12345"));
		usu1.addPerfil(Perfil.ADMIN);
		
		usuarioRepository.saveAll(Arrays.asList(usu1));
		
	}
}

