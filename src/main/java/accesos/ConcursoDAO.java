package accesos;

import java.time.LocalDate;

public interface ConcursoDAO {
    void registrarParticipante(LocalDate fechaInscripcion, int idParticipante, int idConcurso);
}
