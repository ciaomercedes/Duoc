package TeatroMoro;

import java.util.Scanner;

/**
 *
 * @author merce
 */
public class TeatroMoroApp {
    //nuevas variables
    private static final int TOTAL_ASIENTOS = 10;
     

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TeatroMoroService[] asientos = new TeatroMoroService[TOTAL_ASIENTOS];
        
        //Inicializar asientos
        for(int i = 0; i < TOTAL_ASIENTOS; i++){
            asientos[i] = new TeatroMoroService(i+1); //constructor, envia 1 asiento al arreglo
        }
        
        /*
        Bienvenida con menu principal
        */
        System.out.println("Bienvenido al Teatro Moro! :)");
        
        int opcion;
        do {
            System.out.println("");
            mostrarMenu();
            opcion = input.nextInt();
            
            switch (opcion) {
                
                case 1:
                    System.out.println("");
                    System.out.println("Cada asiento tiene un costo de $5.000");
                    System.out.println("Su asiento quedara reservado por 10 minutos");
                    System.out.println("Confirme su compra antes de que venza el tiempo para que no pierda su reserva.");
                    System.out.println("");
                    mostrarAsientosDisponibles(asientos);
                    System.out.println("Ingrese el numero de asiento a reservar (1 - " + TOTAL_ASIENTOS + "):");
                    int numReserva = input.nextInt();
                    if (numReserva >= 1 && numReserva <= TOTAL_ASIENTOS) {
                        if (asientos[numReserva -1].reservar()){
                            System.out.println("Asiento reservado con exito!");
                        } else {
                            System.out.println("Lo sentimos. Asiento ya reservado.");
                        }
                    } else {
                        System.out.println("Numero de asiento invalido.");
                    }
                    break;
                    
                case 2:
                    System.out.println("Ingrese el numero de asiento a modificar (1 - " + TOTAL_ASIENTOS + "):");
                    int numMod = input.nextInt();
                    if (asientos[numMod -1].modificarReserva()){
                        System.out.println("Reserva cancelada. Puede elegir otro asiento");
                    } else {
                        System.out.println("No existe una reserva para modificar");
                    }   
                    break;

                case 3:
                if (mostrarAsientosReservados(asientos)) {
                    //Advertencia y resumen preliminar antes de cnfirmar compra
                    System.out.println("");
                    System.out.println("Estas a punto de confirmar estos asientos: "); // Punto de interrupcion: apra validar que arroje los asientos reservados si es que es más de 1
                    mostrarAsientosReservados(asientos);
                    
                    System.out.println("Desea continuar con la compra? SI / NO");
                    String respuesta = input.next().toUpperCase();

                    while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
                        System.out.println("Lo sentimos, solicitud no es valida. Por favor, escriba SI o NO:");
                        respuesta = input.next().toUpperCase();
                    }

                    if (respuesta.equals("NO")) { // Punto de interrupcion deberia cancelar exitosamente la compra
                        System.out.println("");
                        System.out.println("La compra ha sido cancelada. Lo redigiremos al Menu Principal");
                        System.out.println("");
                        break; //Regresa al menu principal
                    }
                    
                    //confirmar asientos
                    System.out.println("Ingrese el numero de asiento reservado (1 - " + TOTAL_ASIENTOS + "):");
                    int numOcupar = input.nextInt();
                    
                    if (numOcupar >= 1 && numOcupar <= TOTAL_ASIENTOS) { //punto interrup. para capturar el numero de asiento a confirmar, es unitario
                        if (asientos[numOcupar -1].ocupar()){
                            System.out.println("Reserva confirmada para el asiento #" + numOcupar);
                        } else {
                            System.out.println("Ese asiento no esta reservado o ya fue confirmado anteriormente.");
                        } 
                    } else {
                        System.out.println("Numero de asiento invalido");
                    }
                } else {
                    System.out.println("No hay asientos reservados para confirmar. Regresando al menu principal.");
                }
                break;
                    
                case 4:
                    System.out.println("Ingrese el numero de asiento para generar la boleta (1 - " + TOTAL_ASIENTOS + "):"); //punto interrup. deberia imprimir la boleta del asiento confirmacion en opcion 3
                    int numBoleta = input.nextInt();
                    BillingSystem.imprimirBoleta(asientos[numBoleta -1]);
                    break;
                
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                    
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 5);
        
        input.close();
    }

                         
    private static void mostrarMenu(){
        System.out.println("------------------------");
        System.out.println("-----Menu Principal-----");
        System.out.println("------------------------");
        System.out.println("Seleccione una opcion:");
        System.out.println("  1) Reservar asiento");
        System.out.println("  2) Modificar reserva");
        System.out.println("  3) Confirmar reserva");
        System.out.println("  4) Imprimir boleta");
        System.out.println("  5) Salir :(");
        System.out.println("------------------------");
    }
    
    private static void mostrarAsientosDisponibles(TeatroMoroService[] asientos){
        System.out.println("Asientos disponibles:");
        boolean hayDisponibles = false;
        for (TeatroMoroService asiento : asientos) {
            if (!asiento.isReservado() && !asiento.isOcupado()) {
                System.out.println("Asiento # " + asiento.getNumeroAsiento());
                hayDisponibles = true;
            }
        }
        if (!hayDisponibles) {
            System.out.println("Lo sentimos. No hay asientos disponibles");
        }
        System.out.println("");
    }
    
    private static boolean mostrarAsientosReservados(TeatroMoroService [] asientos) {
            System.out.println("Asientos reservados:");
            boolean hayReservados = false;
            for (TeatroMoroService asiento : asientos) {
                if (asiento.isReservado() && !asiento.isOcupado()){
                    System.out.println("Asiento # " + asiento.getNumeroAsiento());
                    hayReservados = true;
                }
            }
            if (!hayReservados) {
                System.out.println("No hay asientos reservados actualmente.");
            }
            System.out.println("");
            return hayReservados;
        }
}