package es.beatkapo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    private List<Dispositivo> dispositivos;
    private int ordenacion;
    private Path rutaImpresion;
}
