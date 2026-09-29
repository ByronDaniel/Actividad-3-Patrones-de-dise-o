package com.patrones.interpreter;

/**
 * NonterminalExpression - Evalúa si la expresión izquierda es igual a la derecha.
 * Soporta comparación de números (por valor) y de objetos (por equals).
 *
 * Participante GoF: NonterminalExpression
 */
public class IgualExpresion implements Expresion {

    private final Expresion izquierda;
    private final Expresion derecha;

    public IgualExpresion(Expresion izquierda, Expresion derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public Object interpretar(Contexto contexto) {
        Object valIzq = izquierda.interpretar(contexto);
        Object valDer = derecha.interpretar(contexto);

        if (valIzq instanceof Number && valDer instanceof Number) {
            return ((Number) valIzq).doubleValue() == ((Number) valDer).doubleValue();
        }

        if (valIzq != null) {
            return valIzq.equals(valDer);
        }
        return valDer == null;
    }

    @Override
    public String toString() {
        return "(" + izquierda + " == " + derecha + ")";
    }
}
