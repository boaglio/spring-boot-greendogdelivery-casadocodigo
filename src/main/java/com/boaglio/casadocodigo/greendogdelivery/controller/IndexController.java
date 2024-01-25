
package com.boaglio.casadocodigo.greendogdelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;
 
@Controller
@RequestMapping("/")
public class IndexController {
 
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("properties")
	@ResponseBody
	Properties properties() {
		return System.getProperties();
	}
    
}