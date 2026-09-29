package com.patrones.memento;

/**
 * Originator - Crea mementos con su estado actual y restaura su estado
 * a partir de un memento. Es la única clase que conoce la estructura
 * interna del memento.
 *
 * Participante GoF: Originator
 */
public class Documento {

    private String texto;
    private String fuente;
    private int tamanoFuente;
    private int posicionCursor;

    public Documento() {
        this.texto = "";
        this.fuente = "Arial";
        this.tamanoFuente = 12;
        this.posicionCursor = 0;
    }

    public void escribir(String nuevoTexto) {
        this.texto = this.texto.substring(0, posicionCursor) + nuevoTexto
                + this.texto.substring(posicionCursor);
        this.posicionCursor += nuevoTexto.length();
        System.out.println("  [Documento] Texto escrito. Cursor en posición " + posicionCursor);
    }

    public void borrarUltimoCaracter() {
        if (posicionCursor > 0 && texto.length() > 0) {
            this.texto = this.texto.substring(0, posicionCursor - 1)
                    + this.texto.substring(posicionCursor);
            this.posicionCursor--;
            System.out.println("  [Documento] Carácter borrado. Cursor en posición " + posicionCursor);
        } else {
            System.out.println("  [Documento] No hay nada que borrar.");
        }
    }

    public void cambiarFuente(String nuevaFuente) {
        this.fuente = nuevaFuente;
        System.out.println("  [Documento] Fuente cambiada a: " + nuevaFuente);
    }

    public void cambiarTamanoFuente(int nuevoTamano) {
        this.tamanoFuente = nuevoTamano;
        System.out.println("  [Documento] Tamaño de fuente cambiado a: " + nuevoTamano);
    }

    public void moverCursor(int posicion) {
        if (posicion >= 0 && posicion <= texto.length()) {
            this.posicionCursor = posicion;
            System.out.println("  [Documento] Cursor movido a posición: " + posicion);
        } else {
            System.out.println("  [Documento] Posición inválida: " + posicion + " (rango: 0-" + texto.length() + ")");
        }
    }

    /**
     * Crea un memento con el estado actual del documento.
     * Solo el Originator sabe qué campos incluir en la instantánea.
     */
    public DocumentoMemento guardarEstado() {
        System.out.println("  [Documento] Guardando estado...");
        return new DocumentoMemento(texto, fuente, tamanoFuente, posicionCursor);
    }

    /**
     * Restaura el estado del documento a partir de un memento.
     * Solo el Originator sabe cómo interpretar el contenido del memento.
     */
    public void restaurarEstado(DocumentoMemento memento) {
        this.texto = memento.getTexto();
        this.fuente = memento.getFuente();
        this.tamanoFuente = memento.getTamanoFuente();
        this.posicionCursor = memento.getPosicionCursor();
        System.out.println("  [Documento] Estado restaurado desde memento.");
    }

    public void mostrarEstado() {
        System.out.println("  ┌─────────────────────────────────────────────");
        System.out.println("  │ Texto:   \"" + texto + "\"");
        System.out.println("  │ Fuente:  " + fuente + " (tamaño " + tamanoFuente + ")");
        System.out.println("  │ Cursor:  posición " + posicionCursor);
        System.out.println("  └─────────────────────────────────────────────");
    }

    public String getTexto() {
        return texto;
    }

    public String getFuente() {
        return fuente;
    }

    public int getTamanoFuente() {
        return tamanoFuente;
    }

    public int getPosicionCursor() {
        return posicionCursor;
    }
}
