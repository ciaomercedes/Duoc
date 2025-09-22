package TeatroMoro;

/**
 *
 * @author merce
 */
public class BillingSystem {
    
    public static void imprimirBoleta(TeatroMoroService asiento){
        
        System.out.println("==============================");
        System.out.println("=======BOLETA DE COMPRA=======");
        System.out.println("=============================="); 
        System.out.println("Numero de Asiento: # " + asiento.getNumeroAsiento());
        System.out.println("Costo: $5.000 CLP");
        if (asiento.isOcupado()){  // Punto de interrupcion: deberia imprimir el estado de acuerdo a la opcion 3 del menu
            System.out.println("Estado asiento: Confirmado.");
        } else if (asiento.isReservado()) {
            System.out.println("Estado asiento: Reservado (pendiente confirmacion)."); // Punto de interrupcion: deberia imprimir el estado de acuerdo a la opcion 3 del menu
        } else {
            System.out.println("Estado asiento: Libre / No reservado."); // Punto de interrupcion: deberia imprimir el estado de acuerdo a la opcion 3 del menu
        }
        
    }
}
