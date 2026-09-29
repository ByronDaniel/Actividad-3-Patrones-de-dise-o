package com.patrones.interpreter;

/**
 * TerminalExpression - Representa una variable cuyo valor se obtiene del contexto.
 * El nombre de la variable se resuelve en tiempo de interpretación.
 *
 * Participante GoF: TerminalExpression
 */
public class VariableExpresion implements Expresion {

    private final String nombre;

    public VariableExpresion(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Object interpretar(Contexto contexto) {
        return contexto.getVariable(nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
