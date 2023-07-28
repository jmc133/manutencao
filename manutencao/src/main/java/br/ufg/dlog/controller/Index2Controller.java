package br.ufg.dlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index2Controller {
	@RequestMapping("**/index2")
	public String index() {
		return "index2.html";
	}

}
