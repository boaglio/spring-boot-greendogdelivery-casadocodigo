
package com.boaglio.casadocodigo.greendogdelivery.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boaglio.casadocodigo.greendogdelivery.dto.MensagemDTO;
 
@EnableAutoConfiguration
@RefreshScope 
@Controller
@RequestMapping("/")
public class IndexController {
 
	@Autowired
	private Environment environment;

	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/ambiente")
	public String ambiente() {
		return "ambiente";
	}

	@GetMapping("properties")
	@ResponseBody
	Properties properties() {
		return System.getProperties();
	}

	@GetMapping("/delivery")
	public String delivery() {
		return "delivery/index";
	}


	@GetMapping("/profile")
	@ResponseBody
	public String[] profile() {
		return this.environment.getActiveProfiles();
	}

	@GetMapping("/servidor")
	@ResponseBody
	public String server(HttpServletRequest request) {
		return request.getServerName() + ":" + request.getServerPort();
	}

    @Value("${mensagem:nenhuma}")
    private String message;

    @Value("${debug:0}")
    private String debug;

    @GetMapping("/oferta")
    @ResponseBody
    public MensagemDTO getMessage(HttpServletRequest request) {
        return new MensagemDTO(this.message,request.getServerName() + ":" + request.getServerPort(),this.debug);
    }
}