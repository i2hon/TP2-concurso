package org.example;

import accesos.*;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    final String TOKEN = "d5c899e3a6d30418c0bafc7d087f0cf8";
    final MailtrapConfig config = new MailtrapConfig.Builder()
            .sandbox(true)
            .inboxId(4480239L)
            .token(TOKEN)
            .build();
    final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);
    final EnviarGmail gmail = new MailTrap(client);
    final LocalDate hoy = LocalDate.now();

    private Scanner scanner = new Scanner(System.in);

    // este es el menu donde escojes el tipo de archivo que vas a crear
    // para hacer el menu me fui ayudando con chatGpt porque nunca avia escrito por teclado en java
    public void menuInicio() throws Exception {
        int opcion=0;
        while (opcion != 4){
            System.out.println("\nMenu Principal\n1 - Concurso (Archivo)\n2 - Concurso (Base de Datos)"+
                    "\n3 - Finalizar\nOpción: ");
            opcion = scanner.nextInt();
            //abre el menu corespondiente al tipo de archivo que hayas escogido
            switch (opcion) {
                case 1:
                    menuArchivo();
                    break;
                case 2:
                    menuDao();
                    break;
                case 3:
                    System.out.println("Finalizando");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }
    }

    //menu de archivo
    public void menuArchivo() throws Exception {
        //crea un concurso de ejemplo esto tendria que costruirse manual
        Concurso concurso = new Concurso(
                1, hoy, hoy.plusDays(5), new RegistrarConcursanteArchivo(),
                gmail);

        int opcion=0;
        while (opcion != 2){
            System.out.println("\nMenu Archivo");
            System.out.println("1 - Registrar concursante \n2 - volver\n Opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("DNI: ");
                    int dni = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();
                    Participante p = new Participante(nombre,apellido,dni);
                    concurso.registrarConcursante(p);
                    System.out.println("Participante registrado");
                    break;

                case 2:
                    System.out.println("Regresando al menu principal");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    //menu de base de datos
    public void menuDao() throws Exception {

        Concurso concurso = new Concurso(
                1, hoy, hoy.plusDays(5), new RegistrarConcursanteDao(new ConcursoDAOJDBC()),
                gmail);

        int opcion = 0;

        while (opcion != 2){
            System.out.println("\nMenu Dao");
            System.out.println("1 - Registrar concursante \n2 - volver\n Opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("DNI: ");
                    int dni = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();
                    Participante p = new Participante(nombre,apellido,dni);
                    concurso.registrarConcursante(p);
                    System.out.println("Participante registrado");
                    break;

                case 2:
                    System.out.println("Regresando al menu principal");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        }
    }
}
