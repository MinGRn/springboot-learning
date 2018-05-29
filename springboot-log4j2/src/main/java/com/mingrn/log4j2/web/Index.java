package com.mingrn.log4j2.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Index {

	private static final Logger LOGGER = LoggerFactory.getLogger(Index.class);
	@GetMapping("/index")
	public String index(){
		LOGGER.info("---------------index-----------------");
		return "index";
	}
}
