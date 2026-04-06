package org.example;

public class Participante {
    private int puntaje = 0;
    private String nombre;
    private String apellido;
    private int dni;
    private int id;

    public Participante (String nombre, String apellido, int dni){
        validarDni(dni);
        this.nombre=nombre;
        this.apellido=apellido;
        this.dni=dni;
    }

    private void validarDni(int dni) {
        if(dni<=0){
            throw new IllegalStateException("se ingreso un Dni invalido");
        }
    }

    public void sumarPuntuacion(int i) {
        this.validarPuntacion(i);
        this.puntaje+=i;
    }

    public void validarPuntacion(int i){
        if(i<=0){
            throw new IllegalStateException("no puede acignarce puntuaciones negativas o 0");
        }
    }
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Participante otro = (Participante) obj;
        if(this.dni==otro.dni){
            return true;
        }
        else{
            return false;
        }
    }
    int getPuntaje(){
        return this.puntaje;
    }

    public int getDni(){
        return this.dni;
    }



    //hacerle un test
    public String toString(){
        return ("Nombre: "+this.nombre+"\nApellido: "+this.apellido+"\nDni: "+this.dni+"\nPuntuacion: "+this.puntaje);
    }
}