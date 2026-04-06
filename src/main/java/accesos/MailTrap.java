package accesos;

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;

import java.util.List;

public class MailTrap implements EnviarGmail{
    private MailtrapClient client;

    public MailTrap(MailtrapClient client) {
        this.client = client;
    }

    @Override
    public void enviarEmail(String destinatario, String asunto, String mensaje) {
        MailtrapMail mail = MailtrapMail.builder()
                .from(new Address("hello@example.com", "Nuevo concursante"))
                .to(List.of(new Address(destinatario)))
                .subject(asunto)
                .text(mensaje)
                .category("Concurso")
                .build();
        try {
            client.send(mail);
        } catch (Exception e) {
            System.out.println("error al enviar mensaje : " + e);
        }
    }
}
