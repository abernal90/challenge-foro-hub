CREATE TABLE respuesta (
  id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  mensaje        VARCHAR(400)    NOT NULL,
  topico         BIGINT UNSIGNED NOT NULL,  -- FK a topico(id)
  fecha_creacion DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  autor          BIGINT UNSIGNED NOT NULL,  -- FK a usuario(id)
  solucion       tinyint         NOT NULL,
  PRIMARY KEY (id),
  KEY idx_respuesta_topico (topico),
  KEY idx_respuesta_autor (autor),
  CONSTRAINT fk_respuesta_topico
    FOREIGN KEY (topico) REFERENCES topico(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_respuesta_usuario
    FOREIGN KEY (autor) REFERENCES usuario(id)
    ON UPDATE CASCADE ON DELETE RESTRICT
);