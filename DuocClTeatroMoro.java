package duoc.cl.teatromoro;

import java.util.Scanner;

public class DuocClTeatroMoro {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean salir = false;

        /*
        Paso 1: Bienvenida con menu principal
        e inicio de ciclo 'for'
        */
        for (int i = 0; i < 5 && !salir; i++) {
            System.out.println("Bienvenido al Teatro Moro! :)");
            System.out.println("");
            System.out.println("1) Comprar Entrada");
            System.out.println("2) Salir");
            System.out.println("");
            
            int solicitud = 0;
            
            while (true) {
                System.out.println("");
                System.out.println("Digite 1 o 2 de acuerdo a su solicitud");
                if (input.hasNextInt()) {
                    solicitud = input.nextInt();
                    if (solicitud == 1 || solicitud == 2) {
                        break; //que sea entrada valida
                    } else {
                        System.out.println("");
                        System.out.println("Error. indique 1 si quiere comprar o 2 para salir del sistema");
                    }
                }else {
                    System.out.println("");
                    System.out.println("Error. Entrada no valida. Ingrese un numero");
                    input.next();
                 }
            }
        
            /*
            Paso 2: Empezamos con la compra de entradas y...
            -Ubicacion de asiento 
            -Ingreso de la edad para aplicacion de descuento 
            -Precio final
            */
            if (solicitud == 1) {
                System.out.println("");
                System.out.println("Ha seleccionado -Comprar Entrada-");

                // Paso 2: Mostrar plateas
                System.out.println("Plateas disponibles:");
                System.out.println("");
                System.out.println("A - Platea VIP ($25.000)");
                System.out.println("B - Platea Media ($15.000)");
                System.out.println("C - Platea Baja ($11.000)");
                System.out.println("");
                System.out.println("Ingrese la platea de su preferencia (A, B o C):");

                String platea = input.next().toUpperCase();
                int precioBase = 0;
                double descuento = 0.0;

                // Validacion de las plateas
                if (platea.equals("A")) {
                    precioBase = 25000;
                } else if (platea.equals("B")) {
                    precioBase = 15000;
                } else if (platea.equals("C")) {
                    precioBase = 11000;
                } else {
                    System.out.println("");
                    System.out.println("Lo sentimos, su solicitud es invalida. Intente de nuevo...");
                    continue;
                }

                //Solicitud de la edad del comprador
                int edad = 0;
                while (true) {
                    System.out.println("");
                    System.out.println("El descuento 'Estudiante' es a partir de 15 anios");
                    System.out.println("El descuento 'Tercera Edad' es a partir de 60 anios");
                    System.out.println("");
                    System.out.println("Indique la edad: ");
                    if (input.hasNextInt()) {
                        edad = input.nextInt();

                        if (edad <= 0 || edad > 100) {
                            System.out.println("");
                            System.out.println("La edad ingresada no es valida. Intente de nuevo...");
                            continue;
                        } else {
                            if (edad >= 60) {
                                descuento = 0.15;
                            } else if (edad >= 15 && edad <= 59) {
                                descuento = 0.10;
                            }
                            break;
                        }
                    } else {
                        System.out.println("Por favor, ingrese un numero valido para la edad.");
                        input.next();
                    }
                }

                //Calculo del precio final
                double precioFinal = precioBase;
                int contador = 0;
                while (contador < 1) {
                    precioFinal = precioBase - (precioBase * descuento);
                    contador++;
                }
                    
                //Resumen de la compra
                System.out.println("");
                System.out.println("----- RESUMEN DE SU COMPRA -----");
                System.out.println("Platea seleccionada: " + platea);
                System.out.println("Precio normal: $" + precioBase);
                System.out.println("Descuento aplicado: " + (int) (descuento * 100) + "%");
                System.out.println("Precio final a pagar: $" + (int) precioFinal);
                System.out.println("-----------------------------");
                
                
            //Preguntar si desea comprar de nuevo
            System.out.println("");
            System.out.println("Desea comprar nuevamente? SI / NO");
            
            String respuesta = input.next().toUpperCase();
            
            // Validar entrada
            while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
                System.out.println("Lo sentimos, solicitud invalida. Por favor escriba SI o NO:");
                respuesta = input.next().toUpperCase();
            }
            
            if (respuesta.equals("NO")){
                System.out.println("");
                System.out.println("Muchas gracias por habernos visitado. Vuelva pronto!");
                salir = true;
            }
                
            } else if (solicitud == 2) {
                System.out.println("");
                System.out.println("Muchas gracias por habernos visitado. Vuelva pronto!");
                salir = true;

            } else {
                System.out.println("");
                System.out.println("La solicitud es invalida. Por favor, intente de nuevo");
            }

            System.out.println("");
        }

        input.close();
        
    }
}