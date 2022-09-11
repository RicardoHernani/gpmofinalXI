package com.ricardochaves.resources;


import java.net.URI;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricardochaves.domain.Cirurgia;
import com.ricardochaves.dto.CirurgiaDTO;
import com.ricardochaves.form.CirurgiaForm;
import com.ricardochaves.resources.utils.URL;
import com.ricardochaves.services.CirurgiaService;

@RestController
@RequestMapping(value="/cirurgias")
public class CirurgiaResource {

	@Autowired
	private CirurgiaService cirurgiaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cirurgia> findById(@PathVariable Integer id) {
		Cirurgia obj = cirurgiaService.findById(id);
		return ResponseEntity.ok().body(obj); 
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CirurgiaDTO>> buscarPorPeriodoPage(
			@RequestParam(value="dataInicial", defaultValue="") String dataInicial,
			@RequestParam(value="dataFinal", defaultValue="") String dataFinal,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="data") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
			LocalDate inicio = URL.convertDate(dataInicial, LocalDate.EPOCH);
			LocalDate fim = URL.convertDate(dataFinal, LocalDate.now());
			Page<CirurgiaDTO> list = cirurgiaService.encontrarPorData(null, inicio, fim, page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/data/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CirurgiaDTO>> buscarPorDataPage(
			@RequestParam(value="dataCirurgia", defaultValue="") String dataCirurgia,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="data") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
			LocalDate inicio = URL.convertDate(dataCirurgia, LocalDate.EPOCH);
			Page<CirurgiaDTO> list = cirurgiaService.encontrarData(null, inicio, page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CirurgiaForm objForm) {
		Cirurgia obj = cirurgiaService.fromForm(objForm);
		obj = cirurgiaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Transactional
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)	
	public ResponseEntity<Void> update(@Valid @RequestBody CirurgiaForm objForm, @PathVariable Integer id) {
		Cirurgia obj = cirurgiaService.fromFormUpdate(objForm);
		obj.setId(id);
		obj = cirurgiaService.update(obj);
		return ResponseEntity.noContent().build(); 
	}
	
	@Transactional
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Cirurgia> delete(@PathVariable Integer id) {
		cirurgiaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}