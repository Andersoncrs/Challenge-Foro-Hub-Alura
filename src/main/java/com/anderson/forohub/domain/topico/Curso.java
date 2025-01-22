package com.anderson.forohub.domain.topico;

public enum Curso {
    LOGICAPROGRAMACIONJAVASCRIPT ("Lógica de programación: sumérgete en la programación con JavaScript"),
    LOGICAPRGRAMACIONFUNCIONESYLISTAS ("Lógica de programación: explorar funciones y listas"),
    ;
    private final String cursoIngresado;


    Curso(String cursoIngresado) {
        this.cursoIngresado = cursoIngresado;
    }


    public static Curso fromInputUser(String cursoIngresado){
        for(Curso curso: values()){
            if(curso.cursoIngresado.equalsIgnoreCase(cursoIngresado)){
                return curso;
            }
        }
        throw new IllegalArgumentException("Valor no Reocnocido: " + cursoIngresado);
    }

    public String getCursoIngresado(){
        return cursoIngresado;
    }
}
