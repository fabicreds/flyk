package com.tcc.flyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ModalConfirmController {
	
	@RequestMapping(value = "/modalConfirm", method = RequestMethod.GET)
	public String iniciarTelaAdmin() {

		return "modalConfirm";
	}
	

}
