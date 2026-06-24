package org.example.sistemaparticipantes.util;

import javafx.scene.control.Alert;

public class Alertas {

    public static void informacion(String mensaje){

        Alert alert=new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();

    }

    public static void error(String mensaje){

        Alert alert=new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();

    }

    public static void advertencia(String mensaje){

        Alert alert=new Alert(Alert.AlertType.WARNING);

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();

    }

}
