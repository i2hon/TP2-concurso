package org.example;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistrarConcursanteArchivo implements RegistrarConcursante{
    @Override
    public void RegistrarConcursante(LocalDate fecha, int idConcurso, int dni) {
        try{
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String texto = fecha.format(formato) + ", " + dni + ", " + idConcurso;
            Files.writeString(
                    Paths.get("inscripciones"+idConcurso+".txt"),
                    texto,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        }catch (Exception e){
            System.out.println("error al guardar el concursante: " + e);
        }
    }
}
