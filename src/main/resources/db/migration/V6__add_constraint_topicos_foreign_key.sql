ALTER TABLE topicos
ADD CONSTRAINT fk_curso
FOREIGN KEY (curso_id) REFERENCES cursos(id)
ON DELETE CASCADE
ON UPDATE CASCADE;