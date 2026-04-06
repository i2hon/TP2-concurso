package org.example;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;

import java.util.List;


import accesos.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws Exception {
        Menu menu = new Menu();
        menu.menuInicio();
    }


}
class MailtrapJavaSDKTest {

    private static final String TOKEN = "d5c899e3a6d30418c0bafc7d087f0cf8";

    public static void main(String[] args) throws Exception {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .sandbox(true)
                .inboxId(4480239L)
                .token(TOKEN)
                .build();

        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);
        EnviarGmail GmailEnviar = new MailTrap(client);
        //FacturasDAO dao = new FacturasDAOJDBC();
        Concurso prueba = new Concurso(1,LocalDate.now(),LocalDate.now().plusDays(3), new RegistrarConcursanteArchivo(), GmailEnviar);
        Participante P = new Participante("calos","Gomes",4321);
        prueba.registrarConcursante(P);

    }
}
