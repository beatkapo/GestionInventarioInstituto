package es.beatkapo.model;

import javafx.collections.FXCollections;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Inventario {
    private List<Dispositivo> dispositivos;
    private int ordenacion;
    private Path rutaImpresion;
    private Date fecha;

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

    public Inventario() {
        dispositivos = FXCollections.observableArrayList();
    }

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
