package com.patrones.interpreter;

/**
 * NonterminalExpression - Evalúa la negación lógica (NOT) de una expresión.
 * La sub-expresión debe resolver a un valor booleano.
 *
 * Participante GoF: NonterminalExpression
 */
public class NotExpresion implements Expresion {

    private final Expresion expresion;

    public NotExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Contexto contexto) {
        Object valor = expresion.interpretar(contexto);

        if (valor instanceof Boolean) {
            return !(Boolean) valor;
        }
        throw new RuntimeException("NOT requiere un operando booleano. Valor=" + valor);
    }

    @Override
    public String toString() {
        return "(NOT " + expresion + ")";
    }
}
