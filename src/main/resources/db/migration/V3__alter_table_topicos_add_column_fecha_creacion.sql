ALTER TABLE topicos
ADD COLUMN fecha_creacion TIMESTAMP;

UPDATE topicos
SET fecha_creacion = NOW();