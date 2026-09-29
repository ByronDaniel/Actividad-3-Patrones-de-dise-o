package com.patrones.interpreter;

/**
 * NonterminalExpression - Evalúa si la expresión izquierda es menor que la derecha.
 * Ambas sub-expresiones deben resolver a valores numéricos (Double).
 *
 * Participante GoF: NonterminalExpression
 */
public class MenorQueExpresion implements Expresion {

    private final Expresion izquierda;
    private final Expresion derecha;

    public MenorQueExpresion(Expresion izquierda, Expresion derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public Object interpretar(Contexto contexto) {
        Object valIzq = izquierda.interpretar(contexto);
        Object valDer = derecha.interpretar(contexto);

        double numIzq = convertirADouble(valIzq);
        double numDer = convertirADouble(valDer);

        return numIzq < numDer;
    }

    private double convertirADouble(Object valor) {
        if (valor instanceof Number) {
            return ((Number) valor).doubleValue();
        }
        throw new RuntimeException("Se esperaba un valor numérico, se obtuvo: " + valor);
    }

    @Override
    public String toString() {
        return "(" + izquierda + " < " + derecha + ")";
    }
}
