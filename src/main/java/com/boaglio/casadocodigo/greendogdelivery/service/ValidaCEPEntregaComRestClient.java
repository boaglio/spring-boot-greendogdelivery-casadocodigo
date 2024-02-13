package com.boaglio.casadocodigo.greendogdelivery.service;

import com.boaglio.casadocodigo.greendogdelivery.domain.CEP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidaCEPEntregaComRestClient implements ValidaCEPEntrega  {

	Logger log = LoggerFactory.getLogger(ValidaCEPEntregaComRestClient.class.getSimpleName());

	private static final String LOCALIDADE_FIXA = "Aracaju";

    private static final String UF_FIXA = "SE";
    
    private static final String URL_VIACEP1 = "https://viacep.com.br/ws/";
    
    private static final String URL_VIACEP2 = "/json/";
	
	public boolean processa(String cep) {
		
		boolean resultado = false;
		
		var urlBuscaCEP = URL_VIACEP1 + cep + URL_VIACEP2;

		log.info(" buscando CEP em ["+urlBuscaCEP+"]");
		
		var restClient =  RestClient.create();

		ResponseEntity<CEP> responseCEP = restClient
				.get()
				.uri(urlBuscaCEP)
				.retrieve().toEntity(CEP.class);

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
