package es.beatkapo.model;

import javafx.collections.FXCollections;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un inventario de dispositivos
 * @version 1.0
 * @since 1.0
 * @author Fran Gabarda
 * @see Dispositivo
 * @see lombok
 */

@Data
@AllArgsConstructor
public class Inventario {
    /**
     * Lista de dispositivos
     */
    private List<Dispositivo> dispositivos;
    /**
     * Tipo de ordenación, 0 para ID, 1 para fecha, 2 para precio, 3 para tipo, 4 para marca, 5 para modelo
     */
    private int ordenacion;
    /**
     * Ruta de impresión
     */
    private Path rutaImpresion;
    /**
     * Fecha del inventario
     */
    private Date fecha;

    /**
     * Método que devuelve un string con la información del inventario
     * @return String con la información del inventario
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String ordenacionString = "";
        StringBuilder dispositivosString = new StringBuilder();
        for (Dispositivo dispositivo : dispositivos) {
            dispositivosString.append(dispositivo.toString()).append("\n");
        }
        switch (ordenacion) {
            case 0:
                ordenacionString = "ID";
                break;
            case 1:
                ordenacionString = "Fecha";
                break;
            case 2:
                ordenacionString = "Precio";
                break;
            case 3:
                ordenacionString = "Tipo";
                break;
            case 4:
                ordenacionString = "Marca";
                break;
            case 5:
                ordenacionString = "Modelo";
                break;
        }
        return "Inventario del día " + sdf.format(fecha) + "\n\n" +

                "\tDispositivos ordenados por " + ordenacionString + ":\n" + dispositivosString.toString();
    }

    /**
     * Constructor por defecto
     */
    public Inventario() {
        dispositivos = FXCollections.observableArrayList();
    }

    /**
     * Método que ordena la lista de dispositivos
     */
    public void ordenar() {
        switch (ordenacion) {
            case 0:
                dispositivos.sort((d1, d2) -> d1.getId() - d2.getId());
                break;
            case 1:
                dispositivos.sort((d1, d2) -> d1.getFecha().compareTo(d2.getFecha()));
                break;
            case 2:
                dispositivos.sort((d1, d2) -> (int) (d1.getPrecio() - d2.getPrecio()));
                break;
            case 3:
                dispositivos.sort((d1, d2) -> d1.getTipo().compareTo(d2.getTipo()));
                break;
            case 4:
                dispositivos.sort((d1, d2) -> d1.getMarca().compareTo(d2.getMarca()));
                break;
            case 5:
                dispositivos.sort((d1, d2) -> d1.getModelo().compareTo(d2.getModelo()));
                break;
        }
    }
}
