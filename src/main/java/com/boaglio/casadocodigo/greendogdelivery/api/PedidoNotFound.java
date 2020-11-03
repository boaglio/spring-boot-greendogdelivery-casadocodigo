package com.boaglio.casadocodigo.greendogdelivery.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PedidoNotFound {

	  @ResponseBody
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  String pedidoNotFoundHandler(PedidoNotFoundException ex) {
	    return ex.getMessage();
	  }
}
