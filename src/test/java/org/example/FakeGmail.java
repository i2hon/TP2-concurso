package org.example;

import accesos.EnviarGmail;

import java.time.LocalDate;

public class FakeGmail implements EnviarGmail {
    private String destinatario;
    private String asunto;
    private String mensaje;

    @Override
    public void enviarEmail(String destinatario, String asunto, String mensaje) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    String destino(){
        return destinatario;
    }

    String elAsunto(){
        return asunto;
    }

    String elMensaje(){
        return mensaje;
    }
}
