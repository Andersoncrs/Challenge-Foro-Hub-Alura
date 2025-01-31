ALTER TABLE usuarios
ADD CONSTRAINT unique_nombre_usuario UNIQUE (nombre_usuario);