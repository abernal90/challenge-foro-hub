package com.foro.hub.api.domain.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{
	
	public Optional<Perfil> findByNombre(String nombre);

}
