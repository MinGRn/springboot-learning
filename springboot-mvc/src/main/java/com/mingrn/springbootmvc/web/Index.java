package com.mingrn.springbootmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

	@GetMapping("/index")
	public String index(Model model) {
		createException();
		model.addAttribute("model", "==================");
		return "/index";
	}

	private void createException(){
		int i = 5/0;
	}
}
