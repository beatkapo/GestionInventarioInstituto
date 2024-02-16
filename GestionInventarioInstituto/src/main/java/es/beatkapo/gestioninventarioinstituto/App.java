package es.beatkapo.gestioninventarioinstituto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que representa la aplicación
 */
public class App extends Application {
    /**
     * Método que inicia la aplicación
     * @param stage Escenario de la aplicación
     * @throws IOException Excepción de entrada/salida de datos
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("vista-principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestión de inventario del instituto");
        stage.setScene(scene);
        Image icon = new Image("file:src/main/resources/es/beatkapo/gestioninventarioinstituto/icon.png");
        stage.getIcons().add(icon);
        stage.show();
    }

    /**
     * Método que lanza la aplicación
     * @param args Argumentos de la aplicación (no se utilizan en este caso)
     */
    public static void main(String[] args) {
        launch();
    }
}