package com.foro.hub.api.domain.usuario;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestAltaUsuario (@NotBlank String nombre, @Email @NotBlank String correoElectronico, @NotBlank String clave, List<String> perfiles){

}
