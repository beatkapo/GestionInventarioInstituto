package es.beatkapo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dispositivo {
    private int id;
    private Date fecha;
    private float precio;
    private Tipo tipo;
    private String marca;
    private String modelo;

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
