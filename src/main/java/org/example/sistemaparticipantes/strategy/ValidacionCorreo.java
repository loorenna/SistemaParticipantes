package org.example.sistemaparticipantes.strategy;

public class ValidacionCorreo implements ValidacionStrategy {

    @Override
    public boolean validar(String dato) {

        return dato.contains("@");

    }

}