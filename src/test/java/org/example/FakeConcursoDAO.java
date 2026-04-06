package org.example;

import accesos.ConcursoDAO;

import java.time.LocalDate;

public class FakeConcursoDAO implements ConcursoDAO {
    LocalDate fechaIscripcion;
    int idParticipante;
    int idConcurso;

    @Override
    public void registrarParticipante(LocalDate fechaInscripcion, int idParticipante, int idConcurso) {
        this.fechaIscripcion = fechaInscripcion;
        this.idParticipante = idParticipante;
        this.idConcurso = idConcurso;
    }

    LocalDate iscripcionFecha(){
        return fechaIscripcion;
    }

    int participanteId(){
        return idParticipante;
    }

    int concursoId(){
        return idConcurso;
    }
}
