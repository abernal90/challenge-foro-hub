package com.foro.hub.api.domain.usuario;

import java.util.List;

public record ResponseAltaUsuario(Long id, String correoElectronico, List<String> perfiles ) {
	
	public ResponseAltaUsuario (Usuario usuario) {
		this( usuario.getId(), 
			  usuario.getCorreoElectronico(), 
			  usuario.getPerfiles().stream().map(perfil->perfil.getNombre()).toList());
	}

}
