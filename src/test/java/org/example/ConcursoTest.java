package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConcursoTest {
    // el participante se inscribe al cuncurso 1 dia despues de aver avierto las iscripciones
    @Test
    void participanteSeInscribe()throws Exception{
        Concurso concurso = new Concurso(
                1,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(5),
                new FakeArchivo(),
                new FakeGmail());

        Participante p = new Participante("Juan","Perez",1234);
        concurso.registrarConcursante(p);

        //comprueba si al participante se inscribio al concurso corectamente
        //anyMatch comprueba que algun elemento cumppla la condicion
        assertTrue(concurso.ConcursantesIscritos().anyMatch(comprueba -> comprueba.equals(p)));
    }

    //el partisipante se iscribe el dia que se abrieron las iscripciones

    @Test
    void participanteSeInscribePrimerDia()throws Exception{
        Concurso concurso = new Concurso(
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                new FakeArchivo(),
                new FakeGmail());

        Participante p = new Participante("Juan","Perez",1234);

        concurso.registrarConcursante(p);

        //comprueba si al participante se inscribio al concurso corectamente
        assertTrue(concurso.ConcursantesIscritos().anyMatch(comprueba -> comprueba.equals(p)));

        //comprueba si al participante se le acignaron los 10 puntos
        assertEquals(10, p.getPuntaje());
    }

    //el partisipante se iscribe en el concurso pero ya esta cerrada las iscripciones
    @Test
    void participanteSeInscribeEnDiaInvalido() throws Exception {
        Concurso concurso = new Concurso(
                1,
                LocalDate.now().minusDays(4),
                LocalDate.now().minusDays(3),
                new FakeArchivo(),
                new FakeGmail());

        Participante p = new Participante("Juan","Perez",1234);

        //verifica que inscribirse fuera de fecha lanza excepció
        assertThrows(IllegalStateException.class, () -> {
            concurso.registrarConcursante(p);
        });
    }

    @Test
    void cantidadParticipantesStream() throws Exception {
        //creo el concurso
        Concurso concurso = new Concurso(1, LocalDate.now(),
                LocalDate.now().plusDays(2), new FakeArchivo(), new FakeGmail());

        //agrego 2 participantes
        concurso.registrarConcursante(new Participante("Juan","Perez",1234));
        concurso.registrarConcursante(new Participante("Ana","Lopez",5678));
        //compruebo si la cantidad de participantes inscritos es 2
        assertEquals(2, concurso.ConcursantesIscritos().count());
    }

    @Test
    void participanteYaRegistrado() throws Exception {
        Concurso concurso = new Concurso(
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                new FakeArchivo(),
                new FakeGmail());

        Participante p = new Participante("Juan","Perez",1234);

        concurso.registrarConcursante(p);

        assertThrows(IllegalStateException.class, () -> {
            concurso.registrarConcursante(p);
        });
    }

    @Test
    void fechaInscripcionInvalida() {
        assertThrows(Exception.class, () -> {
            new Concurso(
                    1,
                    LocalDate.now().plusDays(5),
                    LocalDate.now(),
                    new FakeArchivo(),
                    new FakeGmail());
        });
    }

    @Test
    void dniInvalido() {
        assertThrows(IllegalStateException.class, () -> {
            new Participante("Juan","Perez",-5);
        });
    }
    @Test
    void puntuacionInvalida() {
        Participante p = new Participante("Juan","Perez",1234);

        assertThrows(IllegalStateException.class, () -> {
            p.sumarPuntuacion(0);
        });
        assertThrows(IllegalStateException.class, () -> {
            p.sumarPuntuacion(-12);
        });
    }

    @Test
    void participantesDiferentes()throws Exception{
        Participante p1 = new Participante("Juan","Perez",1234);
        Participante p2 = new Participante("Carlos","Menendes",5334);
        //comprieba que los 2 concursantes son diferentes
        assertNotEquals(p1, p2);
    }

    @Test
    void registroFakeArchivo() throws Exception {
        FakeArchivo fake = new FakeArchivo();
        LocalDate hoy = LocalDate.now();

        Concurso concurso = new Concurso(1, hoy,
                hoy.plusDays(2), fake, new FakeGmail());

        Participante p1 = new Participante("Carlos","Menendes",5334);

        concurso.registrarConcursante(p1);

        assertEquals(hoy, fake.FechaConcurso());
        assertEquals(1, fake.concursoId());
        assertEquals(5334, fake.dniParticipante());
    }

    @Test
    void registroFakeDao() throws Exception {
        FakeConcursoDAO fake = new FakeConcursoDAO();
        RegistrarConcursanteDao falso = new RegistrarConcursanteDao(fake) ;
        LocalDate hoy = LocalDate.now();

        Concurso concurso = new Concurso(1, hoy,
                hoy.plusDays(2), falso, new FakeGmail());

        Participante p1 = new Participante("Carlos","Menendes",5334);

        concurso.registrarConcursante(p1);

        assertEquals(hoy, fake.iscripcionFecha());
        assertEquals(5334, fake.participanteId());
        assertEquals(1, fake.concursoId());
    }

    @Test
    void registroFakeGmail() throws Exception {
        FakeGmail fake = new FakeGmail();
        LocalDate hoy = LocalDate.now();

        Concurso concurso = new Concurso(1, hoy,
                hoy.plusDays(2), new FakeArchivo(), fake);

        Participante p1 = new Participante("Carlos","Menendes",5334);

        concurso.registrarConcursante(p1);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String  mensaje = hoy.format(formato) + ", " + 5334 + ", " + 1;

        assertEquals("ian2honcharuk@gmail.com", fake.destino());
        assertEquals("iscripcion en el concurso: " + 1, fake.elAsunto());
        assertEquals(mensaje, fake.elMensaje());
    }
}