CREATE TABLE topicos(
	id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	titulo VARCHAR(100) NOT NULL,
	mensaje TEXT NOT NULL,
	motivo VARCHAR(50) NOT NULL,
	curso_id BIGINT REFERENCES cursos(id)
);