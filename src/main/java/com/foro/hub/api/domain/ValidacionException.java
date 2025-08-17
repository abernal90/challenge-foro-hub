package com.foro.hub.api.domain;

public class ValidacionException extends RuntimeException {
	
	/**
     * Serial
     */
    private static final long serialVersionUID = 4241085494404245131L;

	public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
