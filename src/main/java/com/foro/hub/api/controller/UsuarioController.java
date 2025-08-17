package com.foro.hub.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.foro.hub.api.domain.usuario.RequestAltaUsuario;
import com.foro.hub.api.domain.usuario.ResponseAltaUsuario;
import com.foro.hub.api.domain.usuario.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	public UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<?> altaUsuario(@RequestBody @Valid RequestAltaUsuario requestAltaUsuario,
			UriComponentsBuilder uriComponentsBuilder) {
		ResponseAltaUsuario reponse = usuarioService.guardar(requestAltaUsuario);;
		URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(reponse.id()).toUri();
		return ResponseEntity.created(url).body(reponse);
	}

}
