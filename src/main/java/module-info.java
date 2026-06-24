module org.example.sistemaparticipantes {

    requires javafx.controls;

    requires javafx.fxml;

    requires java.sql;

    opens org.example.sistemaparticipantes to javafx.fxml;

    opens org.example.sistemaparticipantes.controller to javafx.fxml;

    opens org.example.sistemaparticipantes.model to javafx.base;

    exports org.example.sistemaparticipantes;

    exports org.example.sistemaparticipantes.controller;

}