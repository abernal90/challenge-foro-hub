-- -----------------------------
-- Tabla: topico
-- -----------------------------
CREATE TABLE topico (
  id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  titulo         VARCHAR(200)    NOT NULL,
  mensaje        VARCHAR(300)    NOT NULL,
  fecha_creacion DATETIME        NOT NULL,
  status         tinyint         NOT NULL,
  autor          BIGINT UNSIGNED NOT NULL,   -- FK a usuario(id)
  curso          BIGINT UNSIGNED NOT NULL,   -- FK a curso(id)
  PRIMARY KEY (id),
  KEY idx_topico_autor (autor),
  KEY idx_topico_curso (curso),
  CONSTRAINT fk_topico_usuario
    FOREIGN KEY (autor) REFERENCES usuario(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_topico_curso
    FOREIGN KEY (curso) REFERENCES curso(id)
    ON UPDATE CASCADE ON DELETE RESTRICT
);