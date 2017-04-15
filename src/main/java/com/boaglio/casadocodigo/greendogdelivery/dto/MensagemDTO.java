package com.boaglio.casadocodigo.greendogdelivery.dto;

public class MensagemDTO {

	private String mensagem;

	private String servidor;

	private String debug;

	public MensagemDTO(String mensagem,String servidor,String debug) {
		this.mensagem = mensagem;
		this.servidor = servidor;
		this.debug = debug;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	@Override
	public String toString() {
		return "MensagemDTO [mensagem=" + mensagem + ", servidor=" + servidor + ", debug=" + debug + "]";
	}

}
