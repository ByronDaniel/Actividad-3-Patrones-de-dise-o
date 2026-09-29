package com.patrones.interpreter;

/**
 * TerminalExpression - Representa un número literal.
 * Retorna siempre el mismo valor numérico independiente del contexto.
 *
 * Participante GoF: TerminalExpression
 */
public class NumeroExpresion implements Expresion {

    private final double valor;

    public NumeroExpresion(double valor) {
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
