-- -----------------------------
-- Tabla: usuario
-- -----------------------------
CREATE TABLE usuario (
  id                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre             VARCHAR(200)    NOT NULL,
  correo_electronico VARCHAR(250)    NOT NULL,
  contrasena         VARCHAR(500)    NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_usuario_correo (correo_electronico)
) ;
-- -----------------------------
-- Tabla: perfil
-- -----------------------------
CREATE TABLE perfil (
  id     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(200)    NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_perfil_nombre (nombre)
) ;
-- -----------------------------
-- Tabla puente: usuario_perfil (N:M)
-- -----------------------------
CREATE TABLE usuario_perfil (
  usuario_id BIGINT UNSIGNED NOT NULL,
  perfil_id  BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (usuario_id, perfil_id),
  KEY idx_up_perfil (perfil_id),
  CONSTRAINT fk_up_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_up_perfil
    FOREIGN KEY (perfil_id) REFERENCES perfil(id)
    ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO usuario(nombre, correo_electronico, contrasena) VALUES ( 'Ariel Bernal Aguilar', 'arbernal.90@gmail.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');
INSERT INTO perfil(nombre) VALUES( 'ROLE_USER' );
INSERT INTO usuario_perfil(usuario_id,perfil_id) VALUES ( 1, 1 );