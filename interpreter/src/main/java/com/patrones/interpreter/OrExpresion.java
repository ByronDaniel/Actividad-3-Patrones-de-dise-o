package com.patrones.interpreter;

/**
 * NonterminalExpression - Evalúa la disyunción lógica (OR) de dos expresiones.
 * Ambas sub-expresiones deben resolver a valores booleanos.
 *
 * Participante GoF: NonterminalExpression
 */
public class OrExpresion implements Expresion {

    private final Expresion izquierda;
    private final Expresion derecha;

    public OrExpresion(Expresion izquierda, Expresion derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public Object interpretar(Contexto contexto) {
        Object valIzq = izquierda.interpretar(contexto);
        Object valDer = derecha.interpretar(contexto);

        if (valIzq instanceof Boolean && valDer instanceof Boolean) {
            return (Boolean) valIzq || (Boolean) valDer;
        }
        throw new RuntimeException("OR requiere operandos booleanos. Izq=" + valIzq + ", Der=" + valDer);
    }

    @Override
    public String toString() {
        return "(" + izquierda + " OR " + derecha + ")";
    }
}
