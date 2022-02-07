package com.ricardochaves.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ricardochaves.domain.Cirurgia;
import com.ricardochaves.domain.Usuario;
import com.ricardochaves.dto.CirurgiaDTO;
import com.ricardochaves.form.CirurgiaForm;
import com.ricardochaves.repositories.CirurgiaRepository;
import com.ricardochaves.repositories.UsuarioRepository;
import com.ricardochaves.security.UserSS;
import com.ricardochaves.services.exceptions.AuthorizationException;
import com.ricardochaves.services.exceptions.ObjectNotFoundException;

@Service
public class CirurgiaService {

	@Autowired
	private CirurgiaRepository cirurgiaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Cirurgia findById(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		if(user.getId() == (cirurgiaRepository.findById(id)).get().getUsuario().getId()){
			Optional<Cirurgia> obj = cirurgiaRepository.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Cirurgia não encontrada! id: " + id
				+ ", Tipo: " + Cirurgia.class.getName()));
			
		} else throw new AuthorizationException("Você não tem permissão para acessar, acrescentar, alterar ou apagar cirurgias de outros usuários");
	}
	//Ver se é necessário fazer endpoint para buscar por email e não por id como na aula 96
	//ver a necessidade no frontend
	public Page<CirurgiaDTO> encontrarPorData(Integer idUsuario, LocalDate dataInicial, LocalDate dataFinal, Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		idUsuario = user.getId();
		return cirurgiaRepository.dateIntervalSearch(idUsuario, dataInicial, dataFinal, pageRequest);
	}
	
	public Cirurgia insert(Cirurgia obj) {
		obj.setId(null);
		return cirurgiaRepository.save(obj);
	}
	
	public Cirurgia fromForm(CirurgiaForm objForm) {
		Usuario usu = usuarioRepository.getById(objForm.getUsuarioId());
		Cirurgia cir = new Cirurgia(null, objForm.getMatricula(), objForm.getData(), usu);
		
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		if (user.getId() == usu.getId()) {
		return cir;
		
		} throw new AuthorizationException("Você não tem permissão para acrescentar cirurgias para outros usuários");
		
	}
	
	public Cirurgia update(Cirurgia objForm) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Cirurgia newObj = findById(objForm.getId());
		
		if (user.getId() == newObj.getUsuario().getId()) {
			updateData(newObj, objForm);
			return cirurgiaRepository.save(newObj);
		} throw new AuthorizationException("Você não tem permissão para atualizar cirurgias de outros usuários");
	}
	
	public Cirurgia fromFormUpdate(CirurgiaForm objForm) {												 
		return new Cirurgia(null, objForm.getMatricula(), objForm.getData(), null);	
	}
	
	private void updateData(Cirurgia newObj, Cirurgia objForm) {
		newObj.setMatricula(objForm.getMatricula());
		newObj.setData(objForm.getData());
	}
	
	public void delete(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Cirurgia cirurgiaParaDeletar = findById(id);
		
		if (user.getId() == cirurgiaParaDeletar.getUsuario().getId()) {
			cirurgiaRepository.deleteById(id);
		
		}
		
	}
}
