package es.beatkapo.gestioninventarioinstituto;

import es.beatkapo.model.Dispositivo;
import es.beatkapo.model.Inventario;
import es.beatkapo.model.Tipo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Clase que representa el controlador de la vista principal
 * @version 1.0
 * @since 1.0
 * @author Fran Gabarda
 * @see Dispositivo
 * @see Inventario
 * @see Tipo
 */
public class ControladorPrincipal {
    /**
     * ComboBox para seleccionar el tipo de dispositivo
     */
    @FXML
    private ComboBox<Tipo> comboBox;
    /**
     * ComboBox para seleccionar el filtro de ordenación
     */
    @FXML
    private ComboBox<String> filtro;
    /**
     * Botón para editar un dispositivo
     */
    @FXML
    private Button editarButton;
    /**
     * Botón para eliminar un dispositivo
     */
    @FXML
    private Button eliminarButton;
    /**
     * Botón para guardar un dispositivo
     */
    @FXML
    private Button guardarButton;
    /**
     * ListView para mostrar los dispositivos
     */
    @FXML
    private ListView<Dispositivo> listView;
    /**
     * TextField para introducir la marca del dispositivo
     */
    @FXML
    private TextField marcaText;
    /**
     * TextField para introducir el modelo del dispositivo
     */
    @FXML
    private TextField modeloText;
    /**
     * TextField para introducir el precio del dispositivo
     */
    @FXML
    private TextField precioText;
    /**
     * Lista observable de dispositivos
     */
    private ObservableList<Dispositivo> dispositivos;
    /**
     * Lista observable de tipos
     */
    private ObservableList<Tipo> tipos;
    /**
     * Identificador del dispositivo seleccionado en la lista
     */
    private int idSeleccionado;
    /**
     * Inventario
     */
    private Inventario inventario;
    /**
     * Botón para imprimir el inventario en un archivo de texto
     */
    @FXML
    private Button printButton;
    /**
     * Variable para saber si se está editando un dispositivo
     */
    private boolean editando = false;

    /**
     * Método que se ejecuta al inicializar la vista
     */
    public void initialize(){
        inventario = new Inventario();
        dispositivos = FXCollections.observableArrayList(inventario.getDispositivos());
        listView.setItems(dispositivos);
        tipos = FXCollections.observableArrayList(Tipo.values());
        comboBox.setItems(tipos);
        filtro.setItems(FXCollections.observableArrayList("ID", "Fecha", "Precio", "Tipo", "Marca", "Modelo"));
        filtro.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Aquí puedes llamar al método que quieras ejecutar
                inventario.setOrdenacion(filtro.getSelectionModel().getSelectedIndex());
                inventario.ordenar();
                actualizarLista();
            }
        });
        actualizarLista();
    }

    /**
     * Método que actualiza la lista de dispositivos
     */
    private void actualizarLista() {
        dispositivos = FXCollections.observableArrayList(inventario.getDispositivos());
        listView.setItems(dispositivos);
    }

    /**
     * Método que se ejecuta al pulsar el botón de editar
     * @param event Evento que dispara el método
     */
    @FXML
    void editar(ActionEvent event) {
        Dispositivo dispositivo = listView.getSelectionModel().getSelectedItem();
        if(dispositivo != null){
            idSeleccionado = dispositivo.getId();
            marcaText.setText(dispositivo.getMarca());
            modeloText.setText(dispositivo.getModelo());
            precioText.setText(String.valueOf(dispositivo.getPrecio()));
            comboBox.setValue(dispositivo.getTipo());
            editando = true;
        }
    }

    /**
     * Método que se ejecuta al pulsar el botón de eliminar
     * @param event Evento que dispara el método
     */
    @FXML
    void eliminar(ActionEvent event) {
        Dispositivo dispositivo = listView.getSelectionModel().getSelectedItem();
        if(dispositivo != null){
            inventario.getDispositivos().remove(dispositivo);
            actualizarLista();
        }
    }

    /**
     * Método que se ejecuta al pulsar el botón de guardar
     * @param event Evento que dispara el método
     */
    @FXML
    void guardar(ActionEvent event) {
        String error = "";
        if(marcaText.getText().isEmpty()){
            error += "La marca no puede estar vacía.\n";
        }
        if(modeloText.getText().isEmpty()){
            error += "El modelo no puede estar vacío.\n";
        }
        if(precioText.getText().isEmpty()){
            error += "El precio no puede estar vacío.\n";
        }
        if(comboBox.getValue() == null){
            error += "Debes seleccionar un tipo.\n";
        }
        if(!error.isEmpty()){
            System.out.println(error);
            mostrarAlerta("Error", "Error al guardar", error, Alert.AlertType.ERROR);
        }else{
            Dispositivo dispositivo = new Dispositivo();
            dispositivo.setFecha(new Date());
            dispositivo.setMarca(marcaText.getText());
            dispositivo.setModelo(modeloText.getText());
            dispositivo.setPrecio(Float.parseFloat(precioText.getText()));
            dispositivo.setTipo((es.beatkapo.model.Tipo) comboBox.getValue());
            if(editando){
                inventario.getDispositivos().removeIf(d -> d.getId() == idSeleccionado);
                dispositivo.setId(idSeleccionado);
                editando = false;
            }else{
                dispositivo.setId(inventario.getDispositivos().size());
            }
            inventario.getDispositivos().add(dispositivo);
            marcaText.clear();
            modeloText.clear();
            precioText.clear();
            comboBox.setValue(null);
        }
        actualizarLista();
    }

    /**
     * Método que se ejecuta al pulsar el botón de imprimir
     * @param event Evento que dispara el método
     */
    @FXML
    void print(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Plain text", "*.txt"));
        fileChooser.setInitialFileName("inventario.txt");
        fileChooser.setTitle("Guardar inventario");
        fileChooser.setInitialDirectory(new java.io.File(System.getProperty("user.home")));
        try{
            inventario.setRutaImpresion(fileChooser.showSaveDialog(printButton.getScene().getWindow()).toPath());
        }catch (Exception e) {
            System.err.println("Error al guardar el archivo de impresión.");
        }
        File file = inventario.getRutaImpresion().toFile();
        guardarArchivoTxt(file);
    }
    /**
     * Método que guarda el inventario en un archivo de texto
     * @param file Archivo en el que se guarda el inventario
     */
    private void guardarArchivoTxt(File file) {
        Date fecha = new Date();
        inventario.setFecha(fecha);
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(inventario);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que muestra una alerta
     *
     * @param title     Título de la alerta
     * @param header    Cabecera de la alerta
     * @param content   Contenido de la alerta
     * @param alertType Tipo de alerta
     */
    private void mostrarAlerta(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
