package com.ricardochaves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ricardochaves.domain.Cirurgia;
import com.ricardochaves.domain.Usuario;
import com.ricardochaves.enums.Perfil;
import com.ricardochaves.form.UsuarioForm;
import com.ricardochaves.form.UsuarioFormUpdate;
import com.ricardochaves.repositories.CirurgiaRepository;
import com.ricardochaves.repositories.UsuarioRepository;
import com.ricardochaves.security.UserSS;
import com.ricardochaves.services.exceptions.AuthorizationException;
import com.ricardochaves.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CirurgiaRepository cirurgiaRepository;

	public Usuario findById(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! id: " + id
				+ ", Tipo: " + Usuario.class.getName()));
	}
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return usuarioRepository.save(obj);
	}
	
	public Usuario fromForm(UsuarioForm objForm) {
		return new Usuario(objForm.getId(), objForm.getNome(), objForm.getEmail(), pe.encode(objForm.getSenha()));
	}
	
	public Usuario update(Usuario objForm) {
		Usuario newObj = findById(objForm.getId());
		updateData(newObj, objForm);
		return usuarioRepository.save(newObj);
	}
	
	public Usuario fromFormUpdate(UsuarioFormUpdate objForm) {												 
		return new Usuario(null, null, null, pe.encode(objForm.getSenha()));	
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setSenha(obj.getSenha());
	}
	
	public void delete(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) || id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		List<Cirurgia> obj = cirurgiaRepository.findAllCirurgiaByUsuarioId(id);
		
		for(Cirurgia cirurgia : obj ) {
		cirurgiaRepository.delete(cirurgia);
		}
		
		findById(id);
		usuarioRepository.deleteById(id);
	}
	
}
