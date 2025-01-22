package com.anderson.forohub.domain.topico;

public enum Motivo {
    DUDA,
    QUEJA,
    PROYECTO,
    BUG,
    SUGERENCIA;


    public static Motivo fromInputUser(String motivoIngresado){
        for(Motivo motivo: values()){
            if(motivo.name().equalsIgnoreCase(motivoIngresado)){
                return motivo;
            }
        }
        throw new IllegalArgumentException("Motivo no reconocido: " + motivoIngresado);
    }
}


