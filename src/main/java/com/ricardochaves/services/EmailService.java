package com.ricardochaves.services;


import org.springframework.mail.SimpleMailMessage;

import com.ricardochaves.domain.Usuario;

public interface EmailService {

	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Usuario usuario, String newPass);
}
