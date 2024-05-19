import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);
        ServiciosApi service = new ServiciosApi();
        int opcion = 0;
        System.out.println("**** Bienvenido ****");

        try {

            while(true){

                System.out.println("1. Buscar pelicula");
                System.out.println("2. ver peliculas encotradas");
                System.out.println("3. salir");
                opcion = entrada.nextInt();

                switch(opcion){
                    case 1 :
                        System.out.printf("ingrese el numero de la pelicula: \n");
                        int id = entrada.nextInt();
                        Pelicula pelicula = service.buscarPelicula(id);
                        if (pelicula == null){
                            System.out.println("La peicula que buscas no existe");
                            break;

                        }
                        System.out.printf("La pelicula es: %s\n", pelicula.toString() );
                        System.out.printf("\f\f");
                        break;
                    case 2 :
                        System.out.println("las peliculas son: ");
                        System.out.println(service.obtenerPeliculas().toString());
                        System.out.printf("\n\n");
                        break;

                    case 3:
                        System.out.println("¡¡¡Muchas gracias!!!");
                        break;
                    default:
                        break;

                }

                if (opcion == 3){
                    break;
                }

            }

        }
        catch (NumberFormatException e){
            System.out.println("EL valor a igresar debe ser un numero");
        }


    }
}