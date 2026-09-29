package com.patrones.memento;

import java.util.Stack;

/**
 * Caretaker - Gestiona las pilas de mementos para undo y redo.
 * No examina ni modifica el contenido de los mementos;
 * solo los almacena y los devuelve al Originator.
 *
 * Participante GoF: Caretaker
 */
public class HistorialEditor {

    private final Stack<DocumentoMemento> pilaUndo;
    private final Stack<DocumentoMemento> pilaRedo;

    public HistorialEditor() {
        this.pilaUndo = new Stack<>();
        this.pilaRedo = new Stack<>();
    }

    /**
     * Guarda un memento en la pila de undo.
     * Limpia la pila de redo porque un nuevo cambio invalida los redo anteriores.
     */
    public void guardar(DocumentoMemento memento) {
        pilaUndo.push(memento);
        pilaRedo.clear();
        System.out.println("  [Historial] Estado guardado. Undo disponibles: " + pilaUndo.size());
    }

    /**
     * Retorna el memento más reciente de la pila de undo
     * y mueve el estado actual a la pila de redo.
     */
    public DocumentoMemento deshacer() {
        if (pilaUndo.isEmpty()) {
            System.out.println("  [Historial] No hay estados para deshacer.");
            return null;
        }
        DocumentoMemento memento = pilaUndo.pop();
        pilaRedo.push(memento);
        System.out.println("  [Historial] Deshacer. Undo restantes: " + pilaUndo.size()
                + ", Redo disponibles: " + pilaRedo.size());
        return memento;
    }

    /**
     * Retorna el memento más reciente de la pila de redo
     * y lo mueve de vuelta a la pila de undo.
     */
    public DocumentoMemento rehacer() {
        if (pilaRedo.isEmpty()) {
            System.out.println("  [Historial] No hay estados para rehacer.");
            return null;
        }
        DocumentoMemento memento = pilaRedo.pop();
        pilaUndo.push(memento);
        System.out.println("  [Historial] Rehacer. Undo disponibles: " + pilaUndo.size()
                + ", Redo restantes: " + pilaRedo.size());
        return memento;
    }

    public boolean puedeDeshacer() {
        return !pilaUndo.isEmpty();
    }

    public boolean puedeRehacer() {
        return !pilaRedo.isEmpty();
    }

    public void mostrarHistorial() {
        System.out.println("  ╔═══ Historial de estados (Undo) ═══");
        if (pilaUndo.isEmpty()) {
            System.out.println("  ║ (vacío)");
        } else {
            for (int i = pilaUndo.size() - 1; i >= 0; i--) {
                System.out.println("  ║ " + (i + 1) + ". " + pilaUndo.get(i).getDescripcion());
            }
        }
        System.out.println("  ╠═══ Historial de estados (Redo) ═══");
        if (pilaRedo.isEmpty()) {
            System.out.println("  ║ (vacío)");
        } else {
            for (int i = pilaRedo.size() - 1; i >= 0; i--) {
                System.out.println("  ║ " + (i + 1) + ". " + pilaRedo.get(i).getDescripcion());
            }
        }
        System.out.println("  ╚════════════════════════════════════");
    }
}
