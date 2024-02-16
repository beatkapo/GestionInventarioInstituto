package es.beatkapo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa un dispositivo
 * @version 1.0
 * @since 1.0
 * @author Fran Gabarda
 * @see Tipo
 * @see lombok
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dispositivo {
    /**
     * Identificador del dispositivo
     */
    private int id;
    /**
     * Fecha de compra del dispositivo
     */
    private Date fecha;
    /**
     * Precio del dispositivo
     */
    private float precio;
    /**
     * Tipo del dispositivo
     */
    private Tipo tipo;
    /**
     * Marca del dispositivo
     */
    private String marca;
    /**
     * Modelo del dispositivo
     */
    private String modelo;

    /**
     * Método que devuelve un string con la información del dispositivo
     * @return String con la información del dispositivo
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "ID: " + id +
                "\\ Fecha de compra: " + sdf.format(fecha) +
                "\\ Precio: " + precio +
                "\\ Tipo: " + tipo +
                "\\ Marca: '" + marca +
                "\\ Modelo: '" + modelo;
    }
}
