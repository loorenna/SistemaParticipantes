package org.example.sistemaparticipantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Conexion instancia;

    private Connection conexion;

    private final String URL =
            "jdbc:postgresql://localhost:5432/participantesdb";

    private final String USER = "postgres";

    private final String PASSWORD = "123";

    private Conexion() {

        try {

            conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexion exitosa");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    public static Conexion getInstancia() {

        if (instancia == null) {

            instancia = new Conexion();

        }

        return instancia;

    }

    public Connection getConexion() {

        return conexion;

    }

}