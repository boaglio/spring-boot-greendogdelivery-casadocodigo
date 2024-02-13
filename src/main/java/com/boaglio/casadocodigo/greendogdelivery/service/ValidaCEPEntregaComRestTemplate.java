package com.boaglio.casadocodigo.greendogdelivery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boaglio.casadocodigo.greendogdelivery.domain.CEP;

@Service
public class ValidaCEPEntregaComRestTemplate implements ValidaCEPEntrega {

	Logger log = LoggerFactory.getLogger(ValidaCEPEntregaComRestTemplate.class.getSimpleName());

	private static final String LOCALIDADE_FIXA = "Aracaju";

    private static final String UF_FIXA = "SE";
    
    private static final String URL_VIACEP1 = "https://viacep.com.br/ws/";
    
    private static final String URL_VIACEP2 = "/json/";
	
	@Override
	public boolean processa(String cep) {
		
		boolean resultado = false;
		
		var urlBuscaCEP = URL_VIACEP1 + cep + URL_VIACEP2;

		log.info(" buscando CEP em ["+urlBuscaCEP+"]");
		
		var restTemplate = new RestTemplate();
		
		ResponseEntity<CEP> responseCEP = restTemplate.getForEntity(urlBuscaCEP, CEP.class);

		log.info("Resultado GET CEP = [" + responseCEP+"]");
		
		var wsCEP = responseCEP.getBody();
		if (wsCEP!=null) {
		  if (wsCEP.getUf().equals(UF_FIXA) && wsCEP.getLocalidade().equals(LOCALIDADE_FIXA) ) {
			  resultado = true;
		  }
		}

		log.info(" CEP [" + cep + "] valido ? "+ resultado);
		
		return resultado;		
	}
}
