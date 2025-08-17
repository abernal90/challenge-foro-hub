package com.foro.hub.api.domain.topico;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foro.hub.api.domain.ValidacionException;
import com.foro.hub.api.domain.curso.Curso;
import com.foro.hub.api.domain.curso.CursoRepository;
import com.foro.hub.api.domain.usuario.UsuarioRepository;

@Service
public class TopicoService {
	
    @Autowired
    private TopicoRepository topicoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
	
	/**
	 * @param request
	 * @return
	 */
	public ResponseTopico guardar(RequestAltaTopico request) {
    	
		var usuario = usuarioRepository.findById(request.idUsuario());
    	if(usuario.isEmpty()) {
    		throw new ValidacionException("No se encontro el ID del usuario");
    	}
    	
    	List<Curso> listCurso = cursoRepository.findByNombreIgnoreCase(request.nombreCurso());
    	Curso curso = null;
    	if(listCurso.isEmpty()) {
    		 curso = cursoRepository.save(Curso.builder()
        	.nombre(request.nombreCurso())
        	.categoria(request.categoria())
        	.build());
    	} else {
    		curso = listCurso.get(0);
    	}
    	
    	List<Topico> existeTopico = topicoRepository.findByTituloIgnoreCaseAndMensajeIgnoreCase(request.titulo(), request.mensaje());
    	
    	Topico topico = null;
    	if(!existeTopico.isEmpty() && existeTopico.get(0).getStatus()) {
    		throw new ValidacionException("El topico ya se encuentra registrado");
    	} else if(!existeTopico.isEmpty() && !existeTopico.get(0).getStatus()) {
    		topico = existeTopico.get(0);
    		topico.setAutor(usuario.get());
    		topico.setCurso(curso);
    		topico.setStatus(Boolean.TRUE);
    	}else {
    		topico = Topico.builder()
    		.autor(usuario.get())
    		.curso(curso)
    		.titulo(request.titulo())
    		.mensaje(request.mensaje())
    		.fechaCreacion(LocalDateTime.now())
    		.status(Boolean.TRUE)
    		.build();
    	}
    	
    	topico = topicoRepository.save(topico);
    	
    	return new ResponseTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion());
	}
	
	/**
	 * @param paginacion
	 * @return
	 */
	public Page<ResponseTopico> listar(Pageable paginacion){
		return topicoRepository.findByStatusTrue(paginacion).map(ResponseTopico::new);
	}
	
	/**
	 * @param id
	 * @return
	 */
	public ResponseTopico consulta(Long id) {
		var topico = topicoRepository.findByIdAndStatusTrue(id);
    	if(topico.isEmpty()) {
    		throw new ValidacionException("No se encontro el ID del topico");
    	}
    	return new ResponseTopico(topico.get(0));
	}
	
	/**
	 * @param id
	 * @param request
	 * @return
	 */
	public ResponseTopico actualizar(Long id, RequestActualizarTopico request) {
		var topicoOptional = topicoRepository.findById(id);
    	if(topicoOptional.isEmpty()) {
    		throw new ValidacionException("No se encontro el ID del topico");
    	}
    	
		var usuario = usuarioRepository.findById(request.idUsuario());
    	if(usuario.isEmpty()) {
    		throw new ValidacionException("No se encontro el ID del usuario");
    	}
    	
    	var topico = topicoOptional.get();
    	List<Topico> existeTopico = topicoRepository.findByTituloIgnoreCaseAndMensajeIgnoreCase(request.titulo(), request.mensaje());
    	if(!existeTopico.isEmpty() && !id.equals(existeTopico.get(0).getId()) ) {
    		throw new ValidacionException("El topico ya se encuentra registrado");
    	}
    	
    	List<Curso> listCurso = cursoRepository.findByNombreIgnoreCase(request.nombreCurso());
    	Curso curso = null;
    	if(listCurso.isEmpty()) {
    		 curso = cursoRepository.save(Curso.builder()
        	.nombre(request.nombreCurso())
        	.categoria(request.categoria())
        	.build());
    	} else {
    		curso = listCurso.get(0);
    		if(StringUtils.isNotBlank(request.categoria())) {
    			curso.setCategoria(request.categoria());
    		}
    	}
		
    	topico.setMensaje(request.mensaje());
    	topico.setTitulo(request.titulo());
    	topico.setAutor(usuario.get());
    	topico.setCurso(curso);
    	if(request.status() != null) {
    		topico.setStatus(request.status());
    	}
    	
    	return new ResponseTopico(topicoRepository.save(topico));
	}
	
	/**
	 * @param id
	 * @return
	 */
	public ResponseTopico desactivar(Long id) {
		var topicoOptional = topicoRepository.findById(id);
    	if(topicoOptional.isEmpty()) {
    		throw new ValidacionException("No se encontro el ID del topico");
    	}
    	var topico = topicoOptional.get();
    	topico.setStatus(Boolean.FALSE);
    	return new ResponseTopico(topicoRepository.save(topico));
	}

}
