package com.boaglio.casadocodigo.greendogdelivery.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boaglio.casadocodigo.greendogdelivery.domain.CEP;

@Service
public class ValidaCEPEntrega {

	private static String LOCALIDADE_FIXA = "Aracaju";
			
    private static String UF_FIXA = "SE";
    
    private static String URL_VIACEP1 = "https://viacep.com.br/ws/";
    
    private static String URL_VIACEP2 = "/json/";
	
	public boolean processa(String cep) {
		
		boolean resultado = false;
		
		String urlBuscaCEP = URL_VIACEP1 + cep + URL_VIACEP2;
		
		System.out.println(" buscando CEP em ["+urlBuscaCEP+"]");
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<CEP> responseCEP = restTemplate.getForEntity(urlBuscaCEP, CEP.class);
		
		System.out.println("Resultado GET CEP = [" + responseCEP+"]");
		
		CEP wsCEP = responseCEP.getBody(); 
		if (wsCEP!=null) {
		  if (wsCEP.getUf().equals(UF_FIXA) && wsCEP.getLocalidade().equals(LOCALIDADE_FIXA) ) {
			  resultado = true;
		  }
		}
		
		System.out.println(" CEP [" + cep + "] valido ? "+ resultado);
		
		return resultado;		
	}
}
