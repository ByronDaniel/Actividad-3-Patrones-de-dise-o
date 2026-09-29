package com.patrones.interpreter;

/**
 * AbstractExpression - Interfaz que define el contrato para todas las expresiones
 * del patrón Interpreter.
 *
 * Participante GoF: AbstractExpression
 */
public interface Expresion {

    /**
     * Interpreta la expresión en el contexto dado.
     * @param contexto el contexto con las variables y sus valores
     * @return el resultado de la interpretación (Double para numéricos, Boolean para lógicos, String para textos)
     */
    Object interpretar(Contexto contexto);
}
