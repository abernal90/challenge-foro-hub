package com.foro.hub.api.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestAltaTopico(
		@NotNull
		Long idUsuario,
		@NotBlank
		String mensaje, 
		@NotBlank
		String nombreCurso, 
		@NotBlank
		String titulo, 
		String categoria) {

}
