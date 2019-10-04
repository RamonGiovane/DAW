package br.tsi.daw.tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaController {

	public OlaController() {
	
	}
	
	@RequestMapping("/olamundo")
	public String ola() {
		return "ola";
	}
	
}
