package com.foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record ResponseTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion) {
	
	public ResponseTopico(Topico topico) {
		this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion());
	}

}
