package com.ricardochaves.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ricardochaves.domain.Referencia;
import com.ricardochaves.repositories.ReferenciaRepository;
import com.ricardochaves.services.exceptions.ObjectNotFoundException;

@Service
public class ReferenciaService {
	
	@Autowired
	private ReferenciaRepository referenciaRepository;

	public Referencia findById(Integer codigo) {
		Optional<Referencia> obj = referenciaRepository.findById(codigo);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Código não encontrado! código: " + codigo
				+ ", Tipo: " + Referencia.class.getName()));
	}
	
	public Page<Referencia> findPage(String text, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return referenciaRepository.findByDescricaoContainingIgnoreCase(text, pageRequest);
	}
	
	public Referencia insert(Referencia obj) {
		obj.setCodigo(obj.getCodigo());
		return referenciaRepository.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		referenciaRepository.deleteById(id);
	}
	
}
