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

public class ControladorPrincipal {

    @FXML
    private ComboBox<Tipo> comboBox;
    @FXML
    private ComboBox<String> filtro;

    @FXML
    private Button editarButton;

    @FXML
    private Button eliminarButton;

    @FXML
    private Button guardarButton;

    @FXML
    private ListView<Dispositivo> listView;

    @FXML
    private TextField marcaText;

    @FXML
    private TextField modeloText;

    @FXML
    private TextField precioText;
    private ObservableList<Dispositivo> dispositivos;
    private ObservableList<Tipo> tipos;
    private int idSeleccionado;
    private Inventario inventario;

    @FXML
    private Button printButton;

    private boolean editando = false;
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

    private void actualizarLista() {
        dispositivos = FXCollections.observableArrayList(inventario.getDispositivos());
        listView.setItems(dispositivos);
    }


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

    @FXML
    void eliminar(ActionEvent event) {
        Dispositivo dispositivo = listView.getSelectionModel().getSelectedItem();
        if(dispositivo != null){
            inventario.getDispositivos().remove(dispositivo);
            actualizarLista();
        }
    }

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

    private void mostrarAlerta(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

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


}
