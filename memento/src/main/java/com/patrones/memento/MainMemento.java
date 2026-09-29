package com.patrones.memento;

/**
 * Client - Simula las operaciones del usuario sobre el editor de texto.
 * Demuestra el flujo completo de undo/redo usando el patrón Memento.
 *
 * Participante GoF: Client
 */
public class MainMemento {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("  PATRÓN MEMENTO - Editor de Documentos de Texto");
        System.out.println("=============================================================\n");

        Documento documento = new Documento();
        HistorialEditor historial = new HistorialEditor();

        // --- Operación 1: Escribir "Hola" ---
        System.out.println(">>> Operación 1: Escribir 'Hola'");
        historial.guardar(documento.guardarEstado());
        documento.escribir("Hola");
        documento.mostrarEstado();
        System.out.println();

        // --- Operación 2: Escribir " Mundo" ---
        System.out.println(">>> Operación 2: Escribir ' Mundo'");
        historial.guardar(documento.guardarEstado());
        documento.escribir(" Mundo");
        documento.mostrarEstado();
        System.out.println();

        // --- Operación 3: Cambiar fuente a "Times New Roman" ---
        System.out.println(">>> Operación 3: Cambiar fuente a 'Times New Roman'");
        historial.guardar(documento.guardarEstado());
        documento.cambiarFuente("Times New Roman");
        documento.mostrarEstado();
        System.out.println();

        // --- Operación 4: Cambiar tamaño de fuente a 16 ---
        System.out.println(">>> Operación 4: Cambiar tamaño de fuente a 16");
        historial.guardar(documento.guardarEstado());
        documento.cambiarTamanoFuente(16);
        documento.mostrarEstado();
        System.out.println();

        // --- Operación 5: Escribir " - Bienvenido" ---
        System.out.println(">>> Operación 5: Escribir ' - Bienvenido'");
        historial.guardar(documento.guardarEstado());
        documento.escribir(" - Bienvenido");
        documento.mostrarEstado();
        System.out.println();

        // --- Mostrar historial ---
        System.out.println(">>> Estado del historial:");
        historial.mostrarHistorial();
        System.out.println();

        // --- DESHACER 1: Revertir escritura de " - Bienvenido" ---
        System.out.println(">>> DESHACER (1): Revertir escritura de ' - Bienvenido'");
        DocumentoMemento memento = historial.deshacer();
        if (memento != null) {
            documento.restaurarEstado(memento);
        }
        documento.mostrarEstado();
        System.out.println();

        // --- DESHACER 2: Revertir cambio de tamaño de fuente ---
        System.out.println(">>> DESHACER (2): Revertir cambio de tamaño de fuente");
        memento = historial.deshacer();
        if (memento != null) {
            documento.restaurarEstado(memento);
        }
        documento.mostrarEstado();
        System.out.println();

        // --- DESHACER 3: Revertir cambio de fuente ---
        System.out.println(">>> DESHACER (3): Revertir cambio de fuente");
        memento = historial.deshacer();
        if (memento != null) {
            documento.restaurarEstado(memento);
        }
        documento.mostrarEstado();
        System.out.println();

        // --- Mostrar historial después de undos ---
        System.out.println(">>> Estado del historial después de 3 undos:");
        historial.mostrarHistorial();
        System.out.println();

        // --- REHACER 1: Re-aplicar cambio de fuente ---
        System.out.println(">>> REHACER (1): Re-aplicar cambio de fuente");
        memento = historial.rehacer();
        if (memento != null) {
            documento.restaurarEstado(memento);
        }
        documento.mostrarEstado();
        System.out.println();

        // --- REHACER 2: Re-aplicar cambio de tamaño ---
        System.out.println(">>> REHACER (2): Re-aplicar cambio de tamaño de fuente");
        memento = historial.rehacer();
        if (memento != null) {
            documento.restaurarEstado(memento);
        }
        documento.mostrarEstado();
        System.out.println();

        // --- Mostrar historial final ---
        System.out.println(">>> Estado del historial final:");
        historial.mostrarHistorial();
        System.out.println();

        // --- Nueva operación después de redo (invalida redo restante) ---
        System.out.println(">>> Operación 6: Escribir ' al editor' (invalida redo pendiente)");
        historial.guardar(documento.guardarEstado());
        documento.escribir(" al editor");
        documento.mostrarEstado();
        System.out.println();

        System.out.println(">>> Estado del historial (redo debe estar vacío):");
        historial.mostrarHistorial();

        System.out.println("\n=============================================================");
        System.out.println("  Demostración completada.");
        System.out.println("  El Documento (Originator) crea y restaura mementos.");
        System.out.println("  El HistorialEditor (Caretaker) solo almacena mementos.");
        System.out.println("  El encapsulamiento del Documento se preserva intacto.");
        System.out.println("=============================================================");
    }
}
