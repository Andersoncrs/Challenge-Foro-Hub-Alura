ALTER TABLE topicos
ADD COLUMN editado BOOLEAN,
ADD COLUMN solucionado BOOLEAN;

UPDATE topicos
SET editado = false, solucionado = false;