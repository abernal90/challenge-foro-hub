-- -----------------------------
-- Tabla: curso
-- -----------------------------
CREATE TABLE curso (
  id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre    VARCHAR(200)    NOT NULL,
  categoria VARCHAR(150),
  PRIMARY KEY (id),
  UNIQUE KEY uk_curso_nombre (nombre)
) ;