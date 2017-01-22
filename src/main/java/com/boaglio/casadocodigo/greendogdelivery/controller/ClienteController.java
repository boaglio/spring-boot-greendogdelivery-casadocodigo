 

package com.boaglio.casadocodigo.greendogdelivery.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boaglio.casadocodigo.greendogdelivery.domain.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.repository.ClienteRepository;

 
@Controller
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteRepository clienteRepository;

	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
 
	@GetMapping("/")
	public ModelAndView list() {
		Iterable<Cliente> clientes = this.clienteRepository.findAll();
		return new ModelAndView("clientes/list", "clientes", clientes);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Cliente cliente) {
		return new ModelAndView("clientes/view", "cliente", cliente);
	}

	@GetMapping("/novo") 
	public String createForm(@ModelAttribute Cliente cliente) {
		return "clientes/form";
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Cliente cliente, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("clientes/form", "formErrors", result.getAllErrors());
		}
		cliente = this.clienteRepository.save(cliente);
		redirect.addFlashAttribute("globalCliente", "Successfully created a new cliente");
		return new ModelAndView("redirect:/clientes/{cliente.id}", "cliente.id", cliente.getId());
	}
 
	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		this.clienteRepository.delete(id);
		Iterable<Cliente> clientes = this.clienteRepository.findAll();
		return new ModelAndView("clientes/list", "clientes", clientes);
	}

	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Cliente cliente) {
		return new ModelAndView("clientes/form", "cliente", cliente);
	}

}
