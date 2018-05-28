package com.mingrn.mvc.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class Index {

	@GetMapping("/index")
	public String index(Model model) {
		createException();
		model.addAttribute("model", "==================");
		return "/index";
	}

	@GetMapping("/test")
	public String test() {
		return "/test";
	}

	@PostMapping("/crosRequest")
	public void crosRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("跨域请求成功。。。。");
	}

	private void createException() {
		int i = 5 / 0;
	}
}
