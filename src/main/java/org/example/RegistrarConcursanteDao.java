package org.example;

import accesos.ConcursoDAO;

import java.time.LocalDate;

public class RegistrarConcursanteDao implements RegistrarConcursante{
    ConcursoDAO dao;

    public RegistrarConcursanteDao(ConcursoDAO dao) {
        this.dao = dao;
    }

    @Override
    public void RegistrarConcursante(LocalDate fecha, int idConcurso, int dni) {
        dao.registrarParticipante(fecha, dni, idConcurso);
    }
}
