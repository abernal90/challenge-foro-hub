package com.foro.hub.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.foro.hub.api.domain.topico.RequestActualizarTopico;
import com.foro.hub.api.domain.topico.RequestAltaTopico;
import com.foro.hub.api.domain.topico.ResponseTopico;
import com.foro.hub.api.domain.topico.TopicoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
	
	@Autowired
	private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<?> altaTopico(@RequestBody @Valid RequestAltaTopico requestAltaTopico,
    		                                                       UriComponentsBuilder uriComponentsBuilder) {
    	ResponseTopico reponse = topicoService.guardar(requestAltaTopico);
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(reponse.id()).toUri();
        return ResponseEntity.created(url).body(reponse);
    }
    
    @GetMapping
    public ResponseEntity<?> listarTopicos(
    		@PageableDefault(size = 10, page = 0, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listar(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaTopico(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(topicoService.consulta(id));
    } 
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@PathVariable @NotNull Long id, @RequestBody @Valid RequestActualizarTopico requestActualizarTopico) {
        return ResponseEntity.ok(topicoService.actualizar(id, requestActualizarTopico));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> desactivarTopico(@PathVariable @NotNull Long id){
    	return ResponseEntity.ok(topicoService.desactivar(id));
    }
    
    
    
}
