package org.example.sistemaparticipantes.controller;

import org.example.sistemaparticipantes.dao.ParticipanteDAO;
import org.example.sistemaparticipantes.factory.ParticipanteFactory;
import org.example.sistemaparticipantes.model.Participante;
import org.example.sistemaparticipantes.strategy.ValidacionCorreo;
import org.example.sistemaparticipantes.util.Alertas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CrudController implements Initializable {

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextArea txtObservaciones;

    @FXML
    private ComboBox<String> cbEstadoCivil;

    @FXML
    private ComboBox<String> cbCategoria;

    @FXML
    private RadioButton rbMatutina;

    @FXML
    private RadioButton rbVespertina;

    @FXML
    private RadioButton rbNocturna;

    @FXML
    private TableView<Participante> tabla;

    @FXML
    private TableColumn<Participante, Integer> colId;

    @FXML
    private TableColumn<Participante, String> colCedula;

    @FXML
    private TableColumn<Participante, String> colNombre;

    @FXML
    private TableColumn<Participante, String> colApellido;

    @FXML
    private TableColumn<Participante, Integer> colEdad;

    @FXML
    private TableColumn<Participante, String> colCorreo;

    @FXML
    private TableColumn<Participante, String> colEstado;

    @FXML
    private TableColumn<Participante, String> colJornada;

    @FXML
    private TableColumn<Participante, String> colCategoria;

    private final ParticipanteDAO dao = new ParticipanteDAO();

    private final ToggleGroup grupo = new ToggleGroup();

    private int idSeleccionado = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rbMatutina.setToggleGroup(grupo);
        rbVespertina.setToggleGroup(grupo);
        rbNocturna.setToggleGroup(grupo);

        cbEstadoCivil.getItems().addAll(
                "Soltero",
                "Casado",
                "Divorciado",
                "Viudo"
        );

        cbCategoria.getItems().addAll(
                "Infantil",
                "Juvenil",
                "Adulto",
                "Master"
        );

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoCivil"));
        colJornada.setCellValueFactory(new PropertyValueFactory<>("jornada"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        cargarTabla();

        tabla.setOnMouseClicked(e -> seleccionar());


    }

    private void cargarTabla() {

        ObservableList<Participante> lista =
                FXCollections.observableArrayList(dao.listar());

        tabla.setItems(lista);

    }

    private String obtenerJornada() {

        if (rbMatutina.isSelected()) {
            return "Matutina";
        }

        if (rbVespertina.isSelected()) {
            return "Vespertina";
        }

        return "Nocturna";

    }

    @FXML
    private void guardar() {

        if (txtCedula.getText().isEmpty() ||
                txtNombre.getText().isEmpty() ||
                txtApellido.getText().isEmpty() ||
                txtEdad.getText().isEmpty() ||
                txtCorreo.getText().isEmpty() ||
                cbEstadoCivil.getValue() == null ||
                cbCategoria.getValue() == null ||
                grupo.getSelectedToggle() == null) {

            Alertas.advertencia("Complete todos los campos");

            return;
        }

        if (!txtCedula.getText().matches("\\d+")) {

            Alertas.error("La cédula solo debe contener números");

            return;

        }

        if (!txtEdad.getText().matches("\\d+")) {

            Alertas.error("La edad debe ser numérica");

            return;

        }

        int edad = Integer.parseInt(txtEdad.getText());

        if (edad <= 5) {

            Alertas.error("La edad debe ser mayor a 5");

            return;

        }

        ValidacionCorreo correo = new ValidacionCorreo();

        if (!correo.validar(txtCorreo.getText())) {

            Alertas.error("Correo inválido");

            return;

        }

        if (dao.existeCorreo(txtCorreo.getText())) {

            Alertas.error("Ese correo ya existe");

            return;

        }

        Participante p = ParticipanteFactory.crearParticipante();

        p.setCedula(txtCedula.getText());
        p.setNombre(txtNombre.getText());
        p.setApellido(txtApellido.getText());
        p.setEdad(edad);
        p.setCorreo(txtCorreo.getText());
        p.setEstadoCivil(cbEstadoCivil.getValue());
        p.setCategoria(cbCategoria.getValue());
        p.setJornada(obtenerJornada());
        p.setObservaciones(txtObservaciones.getText());

        dao.guardar(p);

        Alertas.informacion("Registro guardado");

        cargarTabla();

        limpiar();

    }

    @FXML
    private void limpiar() {

        txtCedula.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtEdad.clear();
        txtCorreo.clear();
        txtObservaciones.clear();

        cbEstadoCivil.setValue(null);
        cbCategoria.setValue(null);

        grupo.selectToggle(null);

        idSeleccionado = 0;

    }

    private void seleccionar() {

        Participante p = tabla.getSelectionModel().getSelectedItem();

        if (p == null)
            return;

        idSeleccionado = p.getId();

        txtCedula.setText(p.getCedula());
        txtNombre.setText(p.getNombre());
        txtApellido.setText(p.getApellido());
        txtEdad.setText(String.valueOf(p.getEdad()));
        txtCorreo.setText(p.getCorreo());

        cbEstadoCivil.setValue(p.getEstadoCivil());
        cbCategoria.setValue(p.getCategoria());

        switch (p.getJornada()) {

            case "Matutina" -> rbMatutina.setSelected(true);

            case "Vespertina" -> rbVespertina.setSelected(true);

            case "Nocturna" -> rbNocturna.setSelected(true);

        }

        txtObservaciones.setText(p.getObservaciones());

    }
    @FXML
    private void actualizar() {

        if (idSeleccionado == 0) {

            Alertas.advertencia("Seleccione un participante");

            return;

        }

        if (txtCedula.getText().isEmpty() ||
                txtNombre.getText().isEmpty() ||
                txtApellido.getText().isEmpty() ||
                txtEdad.getText().isEmpty() ||
                txtCorreo.getText().isEmpty()) {

            Alertas.advertencia("Complete todos los campos");

            return;

        }

        if (!txtCedula.getText().matches("\\d+")) {

            Alertas.error("La cédula solo debe contener números");

            return;

        }

        if (!txtEdad.getText().matches("\\d+")) {

            Alertas.error("La edad debe ser numérica");

            return;

        }

        int edad = Integer.parseInt(txtEdad.getText());

        if (edad <= 5) {

            Alertas.error("La edad debe ser mayor a 5");

            return;

        }

        ValidacionCorreo validacion = new ValidacionCorreo();

        if (!validacion.validar(txtCorreo.getText())) {

            Alertas.error("Correo inválido");

            return;

        }
        if (dao.existeCorreoActualizar(txtCorreo.getText(), idSeleccionado)) {

            Alertas.error("Ese correo ya pertenece a otro participante");

            return;

        }

        Participante p = new Participante();

        p.setId(idSeleccionado);
        p.setCedula(txtCedula.getText());
        p.setNombre(txtNombre.getText());
        p.setApellido(txtApellido.getText());
        p.setEdad(edad);
        p.setCorreo(txtCorreo.getText());
        p.setEstadoCivil(cbEstadoCivil.getValue());
        p.setCategoria(cbCategoria.getValue());
        p.setJornada(obtenerJornada());
        p.setObservaciones(txtObservaciones.getText());

        dao.actualizar(p);

        Alertas.informacion("Registro actualizado");

        cargarTabla();

        limpiar();

    }
    @FXML
    private void eliminar() {

        if (idSeleccionado == 0) {

            Alertas.advertencia("Seleccione un participante");

            return;

        }

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

        alerta.setHeaderText(null);

        alerta.setContentText("¿Desea eliminar este participante?");

        Optional<ButtonType> opcion = alerta.showAndWait();

        if (opcion.isPresent() && opcion.get() == ButtonType.OK) {

            dao.eliminar(idSeleccionado);

            Alertas.informacion("Registro eliminado");

            cargarTabla();

            limpiar();

        }

    }
}


