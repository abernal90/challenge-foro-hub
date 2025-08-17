package com.foro.hub.api.domain.topico;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.foro.hub.api.domain.curso.Curso;
import com.foro.hub.api.domain.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "topico")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Topico implements Serializable{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -6018881912411630459L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 200)
	private String titulo;

	@Column(nullable = false, length = 300)
	private String mensaje;

	@Column(nullable = false)
	private LocalDateTime fechaCreacion;

	@Column(nullable = false)
	private Boolean status;
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "autor") // fk_topico_usuario
    private Usuario autor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "curso") // fk_topico_curso
    private Curso curso;

}