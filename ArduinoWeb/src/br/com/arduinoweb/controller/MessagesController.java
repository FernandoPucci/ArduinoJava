package br.com.arduinoweb.controller;

import javax.faces.application.FacesMessage;

import br.com.arduinoweb.model.BaseController;

public class MessagesController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8940348656527744609L;

	protected FacesMessage info(String titulo, String mensagem) {
		return new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem);
	}

	protected FacesMessage warn(String titulo, String mensagem) {
		return new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensagem);
	}

	protected FacesMessage error(String titulo, String mensagem) {

		return new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensagem);

	}

	protected FacesMessage fatal(String titulo, String mensagem) {
		return new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensagem);
	}
}
