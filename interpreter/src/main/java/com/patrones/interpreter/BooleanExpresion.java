package com.patrones.interpreter;

/**
 * TerminalExpression - Representa un valor booleano literal.
 * Retorna siempre el mismo valor booleano independiente del contexto.
 *
 * Participante GoF: TerminalExpression
 */
public class BooleanExpresion implements Expresion {

    private final boolean valor;

    public BooleanExpresion(boolean valor) {
        this.valor = valor;
    }

    @Override
    public Object interpretar(Contexto contexto) {
        return valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
