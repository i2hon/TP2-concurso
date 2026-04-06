package org.example;

import accesos.EnviarGmail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistrarConcursanteMailTrap implements RegistrarConcursante{
    EnviarGmail gmail;

    public RegistrarConcursanteMailTrap(EnviarGmail gmail){
        this.gmail = gmail;
    }

    @Override
    public void RegistrarConcursante(LocalDate fecha, int idConcurso, int dni) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String mensaje = fecha.format(formato) + ", " + dni + ", " + idConcurso;
        //llamamos al costructor MailTrap
        gmail.enviarEmail(
                "ian2honcharuk@gmail.com",
                "iscripcion en el concurso: " + idConcurso,
                mensaje
        );

    }
}
