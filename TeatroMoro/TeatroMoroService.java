package TeatroMoro;

/**
 *
 * @author merce
 */
public class TeatroMoroService {
    //Variables de =Reservar Asiento=
    private static int asientosReservados = 0;
    private static int asientosOcupados = 0;
    
    private int numeroAsiento;
    private boolean reservado;
    private boolean ocupado;

    //Constructor
    public TeatroMoroService (int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
        this.reservado = false;
        this.ocupado = false;
    }
    
    public boolean reservar() {
        if (!reservado && !ocupado){
            reservado = true;
            asientosReservados++;
            return true;
        }        
        return false;
    }
    
    public boolean modificarReserva(){
        if (reservado && !ocupado){
            reservado = false;
            asientosReservados--;
            return true;
        }
        return false;
    }
    
    public boolean ocupar(){
        if (reservado && !ocupado){
            reservado = false;
            ocupado = true;
            asientosOcupados++;
            return true;
        }
        return false;
    }
    
    //getter y setter

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public boolean isReservado() {
        return reservado;
    }

    public boolean isOcupado() {
        return ocupado;
    }
}
