package com.patrones.memento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Memento - Almacena una instantánea inmutable del estado del Documento.
 * Solo el Originator (Documento) debería acceder a su contenido
 * para restaurar el estado.
 *
 * Participante GoF: Memento
 */
public class DocumentoMemento {

    private final String texto;
    private final String fuente;
    private final int tamanoFuente;
    private final int posicionCursor;
    private final LocalDateTime fechaCreacion;

    public DocumentoMemento(String texto, String fuente, int tamanoFuente, int posicionCursor) {
        this.texto = texto;
        this.fuente = fuente;
        this.tamanoFuente = tamanoFuente;
        this.posicionCursor = posicionCursor;
        this.fechaCreacion = LocalDateTime.now();
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getDescripcion() {
        String textoPreview = texto.length() > 30 ? texto.substring(0, 30) + "..." : texto;
        return String.format("[%s] Texto='%s', Fuente=%s/%d, Cursor=%d",
                fechaCreacion.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")),
                textoPreview, fuente, tamanoFuente, posicionCursor);
    }

    @Override
    public String toString() {
        return getDescripcion();
    }
}
