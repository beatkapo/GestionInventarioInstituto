package es.beatkapo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {
    Inventario inventario;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
        inventario.setOrdenacion(0);
        inventario.addDispositivo(new Dispositivo(1, new Date(), 0, Tipo.ORDENADOR, "aaa", "aaa"));
        inventario.addDispositivo(new Dispositivo(2, new Date(), 0, Tipo.PANTALLA, "bbb", "bbb"));
        inventario.addDispositivo(new Dispositivo(3, new Date(), 0, Tipo.IMPRESORA, "ccc", "ccc"));
        inventario.addDispositivo(new Dispositivo(4, new Date(), 0, Tipo.PROYECTOR, "ddd", "ddd"));
        inventario.addDispositivo(new Dispositivo(5, new Date(), 0, Tipo.PORTATIL, "eee", "eee"));
    }

    @Test
    void testOrdenar() {
        inventario.ordenar();
        comprobarLista();
        inventario.setOrdenacion(1);
        inventario.ordenar();
        comprobarLista();
        inventario.setOrdenacion(2);
        inventario.ordenar();
        comprobarLista();
        inventario.setOrdenacion(3);
        inventario.ordenar();
        comprobarLista();
        inventario.setOrdenacion(4);
        inventario.ordenar();
        comprobarLista();
        inventario.setOrdenacion(5);
        inventario.ordenar();
        comprobarLista();
    }

    private void comprobarLista() {
        assertAll(
                () -> assertEquals(1, inventario.getDispositivos().get(0).getId()),
                () -> assertEquals(2, inventario.getDispositivos().get(1).getId()),
                () -> assertEquals(3, inventario.getDispositivos().get(2).getId()),
                () -> assertEquals(4, inventario.getDispositivos().get(3).getId()),
                () -> assertEquals(5, inventario.getDispositivos().get(4).getId())
        );
    }
}