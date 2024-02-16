module es.beatkapo.gestioninventarioinstituto {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.beatkapo.gestioninventarioinstituto to javafx.fxml;
    exports es.beatkapo.gestioninventarioinstituto;
}