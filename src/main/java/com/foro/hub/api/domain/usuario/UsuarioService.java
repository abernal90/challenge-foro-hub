package com.foro.hub.api.domain.usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.foro.hub.api.domain.ValidacionException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public ResponseAltaUsuario guardar(RequestAltaUsuario request) {
		
		var existe = usuarioRepository.findByCorreoElectronico(request.nombre());
		if(existe != null) {
			throw new ValidacionException("El usuario ya se encuentra registrado");
		}
		
		var usuario = new Usuario();
		usuario.setNombre(request.nombre());
		usuario.setCorreoElectronico(request.correoElectronico());
		usuario.setContrasena(new BCryptPasswordEncoder().encode(request.clave()));
		
		List<Perfil> perfiles = new ArrayList<Perfil>();
		if(!CollectionUtils.isEmpty(request.perfiles())) {
			new HashSet<>(request.perfiles())
				.forEach(perfil->{
					Optional<Perfil> obtionalPerfil = perfilRepository.findByNombre(perfil);
					if(obtionalPerfil.isPresent()) {
						perfiles.add(obtionalPerfil.get());
					}
				});
			if(perfiles.isEmpty()) {
		    	throw new ValidacionException("Error al guardar usuario, permisos no encontrados");
			}
			
		}else {
			Optional<Perfil> obtionalPerfil = perfilRepository.findByNombre("ROLE_USER");
			perfiles.add(obtionalPerfil.get());
		}
		usuario.setPerfiles(perfiles);
		return new ResponseAltaUsuario(usuarioRepository.save(usuario));
		
	}

}
