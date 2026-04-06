package accesos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ConcursoDAOJDBC implements ConcursoDAO{
    public void registrarParticipante(LocalDate fechaInscripcion, int idParticipante, int idConcurso){
        final String SQL = "INSERT INTO participantes(fecha, idParticipante, idConcurso) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setDate(1, Date.valueOf(fechaInscripcion));
            st.setInt(2, idParticipante);
            st.setInt(3, idConcurso);
            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al registrar concursante");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al registrar concursante", e);
        }
        }
    }

