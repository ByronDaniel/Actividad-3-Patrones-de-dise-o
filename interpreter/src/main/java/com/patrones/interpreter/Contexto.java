package com.patrones.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * Context - Contiene la información global necesaria para la interpretación.
 * Almacena variables y sus valores (precio, categoría, cantidad, esVIP, etc.).
 *
 * Participante GoF: Context
 */
public class Contexto {

    private final Map<String, Object> variables;

    public Contexto() {
        this.variables = new HashMap<>();
    }

    public void setVariable(String nombre, Object valor) {
        variables.put(nombre, valor);
    }

    public Object getVariable(String nombre) {
        if (!variables.containsKey(nombre)) {
            throw new RuntimeException("Variable no definida en el contexto: " + nombre);
        }
        return variables.get(nombre);
    }

    public boolean existeVariable(String nombre) {
        return variables.containsKey(nombre);
    }

    @Override
    public String toString() {
        return "Contexto" + variables;
    }
}
