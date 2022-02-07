package com.ricardochaves.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardochaves.domain.Cirurgia;
import com.ricardochaves.domain.Procedimento;
import com.ricardochaves.domain.Referencia;
import com.ricardochaves.enums.PremioProcedimento;
import com.ricardochaves.enums.TipoProcedimento;
import com.ricardochaves.form.ProcedimentoForm;
import com.ricardochaves.form.ProcedimentoFormUpdate;
import com.ricardochaves.repositories.CirurgiaRepository;
import com.ricardochaves.repositories.ProcedimentoRepository;
import com.ricardochaves.repositories.ReferenciaRepository;
import com.ricardochaves.security.UserSS;
import com.ricardochaves.services.exceptions.AuthorizationException;
import com.ricardochaves.services.exceptions.ObjectNotFoundException;

@Service
public class ProcedimentoService {

	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private ReferenciaRepository referenciaRepository;
	
	@Autowired
	private CirurgiaRepository cirurgiaRepository;
	
	public Procedimento findById(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Procedimento> procedimentoAlvo = procedimentoRepository.findById(id);
		Optional<Cirurgia> cirurgiaAlvo = cirurgiaRepository.findByCirurgiaProcedimentoId(procedimentoAlvo.get().getId());
		
		if (user.getId() == cirurgiaAlvo.get().getUsuario().getId()) {	
			Optional<Procedimento> obj = procedimentoRepository.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Procedimento não encontrada! id: " + id
					+ ", Tipo: " + Procedimento.class.getName()));
		
		} else throw new AuthorizationException("Você não tem permissão para acessar, acrescentar, alterar ou apagar procedimentos de outros usuários");
		
		
	}
	
	public Procedimento insert(Procedimento obj) {
		obj.setId(null);
		return procedimentoRepository.save(obj);
	}
	
	public Procedimento fromForm(ProcedimentoForm objForm) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Cirurgia> cirurgiaAlvo = cirurgiaRepository.findById(objForm.getCirurgiaId());
		
		
		if (user.getId() == cirurgiaAlvo.get().getUsuario().getId()) {
			Referencia ref = referenciaRepository.getById(objForm.getReferenciaCodigo());
			Cirurgia cir = cirurgiaRepository.getById(objForm.getCirurgiaId());
			Procedimento pro = new Procedimento(null, TipoProcedimento.toEnum(objForm.getTipo()), PremioProcedimento.toEnum(objForm.getPremio()), cir, ref);
			cir.getProcedimentos().add(pro);
			return pro;
		
		} else throw new AuthorizationException("Você não tem permissão para acrescentar procedimentos para outros usuários");
		
	}
	
	public Procedimento update(Procedimento obj) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Procedimento newObj = findById(obj.getId());
		
		if (user.getId() == newObj.getCirurgia().getUsuario().getId()) {
			updateData(newObj, obj);
			return procedimentoRepository.save(newObj);
			
		} else throw new AuthorizationException("Você não tem permissão para atualizar procedimentos de outros usuários");		
	}
	
	public Procedimento fromFormUpdate(ProcedimentoFormUpdate objForm) {						 				 
		Referencia ref = referenciaRepository.getById(objForm.getReferenciaCodigo());
		ref.getCodigo();
		return new Procedimento(null, TipoProcedimento.toEnum(objForm.getTipo()), PremioProcedimento.toEnum(objForm.getPremio()), null, ref);	   
	}
	
	private void updateData(Procedimento newObj, Procedimento obj) {
		newObj.setTipo(obj.getTipo());
		newObj.setPremio(obj.getPremio());
		newObj.setReferencia(obj.getReferencia());
	}
	
	public void delete(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Procedimento procedimentoAlvo = findById(id);
		Optional<Cirurgia> cirurgiaAlvo = cirurgiaRepository.findByCirurgiaProcedimentoId(procedimentoAlvo.getId());
		
		if (user.getId() == cirurgiaAlvo.get().getUsuario().getId()) {
			
			procedimentoRepository.deleteById(id);
			
		}
	}
	
}
