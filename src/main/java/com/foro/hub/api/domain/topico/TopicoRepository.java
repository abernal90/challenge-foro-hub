package com.foro.hub.api.domain.topico;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	
  List<Topico> findByTituloIgnoreCaseAndMensajeIgnoreCase(String titulo, String mensaje);
	
	Page<Topico> findByStatusTrue(Pageable paginacion);
	
	List<Topico> findByIdAndStatusTrue(Long id);

}
