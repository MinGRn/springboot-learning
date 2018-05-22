package com.mingrn.springbootmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("model", "==================");
		return "/index";
	}
}
