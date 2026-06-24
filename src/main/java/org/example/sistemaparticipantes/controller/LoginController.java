package org.example.sistemaparticipantes.controller;


import org.example.sistemaparticipantes.util.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void ingresar(ActionEvent event) {

        if (txtUsuario.getText().isEmpty() ||
                txtPassword.getText().isEmpty()) {

            Alertas.advertencia("Complete todos los campos");

            return;

        }

        if (txtUsuario.getText().equals("admin") &&
                txtPassword.getText().equals("1234")) {

            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/org/example/sistemaparticipantes/crud.fxml")
                );

                Scene scene = new Scene(loader.load());

                scene.getStylesheets().add(
                        getClass().getResource("/org/example/sistemaparticipantes/estilos.css")
                                .toExternalForm()
                );

                Stage stage = new Stage();

                stage.setScene(scene);

                stage.setTitle("CRUD Participantes");

                stage.show();

                Stage login =
                        (Stage) txtUsuario.getScene().getWindow();

                login.close();

            } catch (Exception e) {

                e.printStackTrace();

            }

        } else {

            Alertas.error("Usuario o contraseña incorrectos");

        }

    }

    @FXML
    public void salir() {

        System.exit(0);

    }

}
