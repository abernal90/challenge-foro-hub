package com.foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record ResponseAltaTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion) {

}
