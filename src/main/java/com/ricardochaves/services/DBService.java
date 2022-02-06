package com.ricardochaves.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ricardochaves.domain.Cirurgia;
import com.ricardochaves.domain.Procedimento;
import com.ricardochaves.domain.Referencia;
import com.ricardochaves.domain.Usuario;
import com.ricardochaves.enums.Perfil;
import com.ricardochaves.enums.PremioProcedimento;
import com.ricardochaves.enums.TipoProcedimento;
import com.ricardochaves.repositories.CirurgiaRepository;
import com.ricardochaves.repositories.ProcedimentoRepository;
import com.ricardochaves.repositories.ReferenciaRepository;
import com.ricardochaves.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CirurgiaRepository cirurgiaRepository;
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private ReferenciaRepository referenciaRepository;
	
	public void instantiateTestDatabase() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		Referencia ref1 = new Referencia(10000000, "Ureterorrenolitotr Flex Unilat", new BigDecimal(41.67), new BigDecimal(297.82));
		Referencia ref2 = new Referencia(10000001, "Histerec total", new BigDecimal(41.67), new BigDecimal(335.02));
		Referencia ref3 = new Referencia(10000002, "Fístula Arterio-venosa ", new BigDecimal(27.77), new BigDecimal(195.07));
		Referencia ref4 = new Referencia(10000003, "Palatoplastia", new BigDecimal(33.33), new BigDecimal(215.18));
		Referencia ref5 = new Referencia(10000004, "Artrodese coluna", new BigDecimal(33.33), new BigDecimal(364.75));
		Referencia ref6 = new Referencia(10000005, "Prostatec", new BigDecimal(41.67), new BigDecimal(443.25));
		Referencia ref7 = new Referencia(10000006, "linfadenec pélvica", new BigDecimal(33.33), new BigDecimal(249.79));
		Referencia ref8 = new Referencia(10000007, "tumor anorretal", new BigDecimal(16.67), new BigDecimal(56.07));
		Referencia ref9 = new Referencia(10000003, "Amputação do pé", new BigDecimal(33.33), new BigDecimal(103.02));
		
		referenciaRepository.saveAll(Arrays.asList(ref1, ref2, ref3, ref4, ref5, ref6, ref7, ref8, ref9));
		
		Usuario usu1 = new Usuario(null, "Ricardo", "rhac.sistemas@gmail.com", pe.encode("12345"));
		usu1.addPerfil(Perfil.ADMIN);
		
		Usuario usu2 = new Usuario(null, "Débora", "debora@gmail", pe.encode("123456"));
		Usuario usu3 = new Usuario(null, "Ana", "ana@gmail", pe.encode("1234567"));
		
		usuarioRepository.saveAll(Arrays.asList(usu1, usu2, usu3));
		
		
		Cirurgia cir1 = new Cirurgia(null, 11111111, LocalDate.parse("2020-02-20", formatter), usu1);
		Cirurgia cir2 = new Cirurgia(null, 11111111, LocalDate.parse("2021-05-26", formatter), usu1);
		
		Cirurgia cir3 = new Cirurgia(null, 22222222, LocalDate.parse("2009-10-10", formatter), usu2);
		Cirurgia cir4 = new Cirurgia(null, 33333333, LocalDate.parse("2013-08-15", formatter), usu2);

		Cirurgia cir5 = new Cirurgia(null, 44444444, LocalDate.parse("2000-02-07", formatter), usu3);
		Cirurgia cir6 = new Cirurgia(null, 44444444, LocalDate.parse("2000-02-07", formatter), usu3);
		Cirurgia cir7 = new Cirurgia(null, 44444444, LocalDate.parse("2010-12-24", formatter), usu3);
		Cirurgia cir8 = new Cirurgia(null, 55555555, LocalDate.parse("1998-05-13", formatter), usu3);
		Cirurgia cir9 = new Cirurgia(null, 66666666, LocalDate.parse("2019-06-27", formatter), usu3);	
		
		cirurgiaRepository.saveAll(Arrays.asList(cir1, cir2, cir3, cir4, cir5, cir6, cir7, cir8, cir9));
		
		Procedimento pro1 = new Procedimento(null, TipoProcedimento.PRINCIPAL, PremioProcedimento.DINHEIRO, cir1, ref1);
		Procedimento pro2 = new Procedimento(null, TipoProcedimento.SECUNDARIO, PremioProcedimento.DINHEIRO, cir1, ref2);
		Procedimento pro3 = new Procedimento(null, TipoProcedimento.PRINCIPAL, PremioProcedimento.DINHEIRO, cir2, ref3);


		Procedimento pro4 = new Procedimento(null, TipoProcedimento.PRINCIPAL, PremioProcedimento.TAREFABASICA, cir3, ref4);
		Procedimento pro5 = new Procedimento(null, TipoProcedimento.PRINCIPAL, PremioProcedimento.DINHEIRO, cir4, ref5);

		Procedimento pro6 = new Procedimento(null, TipoProcedimento.PRINCIPAL, PremioProcedimento.DINHEIRO, cir5, ref6);
		Procedimento pro7 = new Procedimento(null, TipoProcedimento.SECUNDARIO, PremioProcedimento.TAREFABASICA, cir6, ref7);
		Procedimento pro8 = new Procedimento(null, TipoProcedimento.PRINCIPAL, PremioProcedimento.DINHEIRO, cir7, ref3);
		Procedimento pro9 = new Procedimento(null, TipoProcedimento.PRINCIPAL, PremioProcedimento.TAREFABASICA, cir8, ref8);
		Procedimento pro10 = new Procedimento(null, TipoProcedimento.PRINCIPAL, PremioProcedimento.TAREFABASICA, cir9, ref9);

		cir1.getProcedimentos().addAll(Arrays.asList(pro1, pro2));
		cir2.getProcedimentos().addAll(Arrays.asList(pro3));
		cir3.getProcedimentos().addAll(Arrays.asList(pro4));
		cir4.getProcedimentos().addAll(Arrays.asList(pro5));
		cir5.getProcedimentos().addAll(Arrays.asList(pro6));
		cir6.getProcedimentos().addAll(Arrays.asList(pro7));
		cir7.getProcedimentos().addAll(Arrays.asList(pro8));
		cir8.getProcedimentos().addAll(Arrays.asList(pro9));
		cir9.getProcedimentos().addAll(Arrays.asList(pro10));
		
		procedimentoRepository.saveAll(Arrays.asList(pro1, pro2, pro3, pro4, pro5, pro6, pro7, pro8, pro9, pro10));
		
	}
	
	
}

