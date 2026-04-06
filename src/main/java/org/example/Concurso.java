package org.example;
import accesos.ConcursoDAO;
import accesos.EnviarGmail;


import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import java.util.stream.Stream;

public class Concurso {
    private LocalDate fechaEscripcion;
    private LocalDate fechaFinEscripcion;
    private List<Participante> concursantes;
    private int id;
    private RegistrarConcursante registro;
    private RegistrarConcursante gmail;


    public Concurso(int id,LocalDate fechaEscripcion,LocalDate fechaFinEscripcion, RegistrarConcursante registro, EnviarGmail gmail) throws Exception {
        this.validarFechaInscripcion(fechaEscripcion,fechaFinEscripcion);
        this.fechaEscripcion = fechaEscripcion;
        this.fechaFinEscripcion = fechaFinEscripcion;
        this.concursantes = new ArrayList<>();
        this.id = id;
        this.registro = registro;
        this.gmail = new RegistrarConcursanteMailTrap(gmail);
    }
    /*
    //concurso base de datos
    public Concurso(int id,LocalDate fechaEscripcion,LocalDate fechaFinEscripcion, ConcursoDAO dao) throws Exception {
        this.validarFechaInscripcion(fechaEscripcion,fechaFinEscripcion);
        this.fechaEscripcion = fechaEscripcion;
        this.fechaFinEscripcion = fechaFinEscripcion;
        this.concursantes = new ArrayList<>();
        this.id = id;
        this.registro = new RegistrarConcursanteDao(dao);
    }

    //concurso gmail
    public Concurso(int id, LocalDate fechaEscripcion, LocalDate fechaFinEscripcion, EnviarGmail gmail) throws Exception {
        this.validarFechaInscripcion(fechaEscripcion,fechaFinEscripcion);
        this.fechaEscripcion = fechaEscripcion;
        this.fechaFinEscripcion = fechaFinEscripcion;
        this.concursantes = new ArrayList<>();
        this.id = id;
        this.registro = new RegistrarConcursanteMailTrap(gmail);
    }

     */
    public void validarFechaInscripcion(LocalDate fechaEscripcion,LocalDate fechaFinEscripcion) throws Exception {
        if(fechaEscripcion.isAfter(fechaFinEscripcion)){
            throw new Exception("Fecha de inscripcion es invalida");
        }
    }

    public void registrarConcursante(Participante concursante){
        LocalDate hoy = LocalDate.now();
        int dni = concursante.getDni();
        if(hoy.isBefore(fechaEscripcion)|| hoy.isAfter((fechaFinEscripcion))){
            throw new IllegalStateException("El concurso está cerrado");
        }
        if(!concursantes.contains(concursante)){
            if(hoy.isEqual(fechaEscripcion)){
                concursante.sumarPuntuacion(10);
            }
            this.concursantes.add(concursante);

            registro.RegistrarConcursante(hoy, id, dni);
            gmail.RegistrarConcursante(hoy,id,dni);

        }
        else{
            throw new IllegalStateException("El concursante ya esta registrado");
        }
    }

    public Stream<Participante> ConcursantesIscritos(){
        return concursantes.stream();
    }
    int getID(){

        return this.id;
    }
}