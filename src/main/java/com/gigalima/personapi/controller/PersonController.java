package com.gigalima.personapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
public class PersonController {
	
	@RequestMapping
	public String ola() {
		return "Olá, Projeto";
	}

}
