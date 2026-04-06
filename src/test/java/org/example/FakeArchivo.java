package org.example;

import java.time.LocalDate;

public class FakeArchivo implements RegistrarConcursante{
    private LocalDate fecha;
    private int idConcurso;
    private int dni;

    @Override
    public void RegistrarConcursante(LocalDate fecha, int idConcurso, int dni) {
        this.fecha = fecha;
        this.dni = dni;
        this.idConcurso = idConcurso;
    }

    LocalDate FechaConcurso() {
        return fecha;
    }

    int dniParticipante() {
        return dni;
    }

    int concursoId(){
        return idConcurso;
    }




}
