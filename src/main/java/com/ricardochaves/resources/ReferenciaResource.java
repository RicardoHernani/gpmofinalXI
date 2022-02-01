package com.ricardochaves.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricardochaves.domain.Referencia;
import com.ricardochaves.resources.utils.URL;
import com.ricardochaves.services.ReferenciaService;

@RestController
@RequestMapping(value="/referencias")
public class ReferenciaResource {
	
	@Autowired
	private ReferenciaService referenciaService;

	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ResponseEntity<Referencia> findById(@PathVariable Integer codigo) {
		Referencia obj = referenciaService.findById(codigo);
		return ResponseEntity.ok().body(obj); 
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Referencia>> findPage(
			@RequestParam(value="text", defaultValue="") String text, 
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
			text = URL.decodeParam(text);
			Page<Referencia> list = referenciaService.findPage(text, page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
	}
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Referencia obj) {
		obj = referenciaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{codigo}").buildAndExpand(obj.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Referencia> delete(@PathVariable Integer id) {
		referenciaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
