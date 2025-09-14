package teatromorointerface;

import java.util.Scanner;

public class TeatroMoroInterface {
    // Variables estáticas para almacenar hasta 5 entradas
    static int entradaUno, entradaDos, entradaTres, entradaCuatro, entradaCinco;
    static String entrada1Ubic, entrada2Ubic, entrada3Ubic, entrada4Ubic, entrada5Ubic;
    static double entrada1Precio, entrada2Precio, entrada3Precio, entrada4Precio, entrada5Precio;
    static String entrada1Tipo, entrada2Tipo, entrada3Tipo, entrada4Tipo, entrada5Tipo;

    // Variables matematicas
    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0;
    static int contadorEntradas = 0; // para asignar número único a cada entrada
   

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean salir = false;
        int cantidadEntradas = 0;

        /*
        Paso 1: Bienvenida con menu principal
        e inicio de ciclo 'for'
        */
        System.out.println("Bienvenido al Teatro Moro! :)");
        
        for (int i = 0; i < 5 && !salir; i++) {
            System.out.println("------------------------------------");
            System.out.println("Por favor, elija una de las opciones");
            System.out.println("------------------------------------");
            System.out.println("1) Iniciar Compra de Entradas");
            System.out.println("2) Ver Promociones disponibles");
            System.out.println("3) Buscar Entrada Existente");
            System.out.println("4) Eliminar Entrada Existente");
            System.out.println("5) Salir :(");
            System.out.println("------------------------------------");
            
            int solicitud = 0;
            
            while (true) {
                System.out.println("");
                System.out.println("Digite el numero asociado a la solicitud que desea:");
                if (input.hasNextInt()) {
                    solicitud = input.nextInt();
                    if (solicitud >= 1 && solicitud <= 5) {
                        break; //que sea entrada valida
                    } else {
                        System.out.println("");
                        System.out.println("Error. Indique solo el numero asociado a su solicitud");
                    }
                }else {
                    System.out.println("");
                    System.out.println("Error. Entrada no valida. Ingrese un numero");
                    input.next();
                 }
            }
        
            //OPCION 1
            if (solicitud == 1) {
                System.out.println("");
                System.out.println("Ha seleccionado *Iniciar Compra de Entradas*");

                // Paso 1: Mostrar Zonas
                System.out.println("---------------------------");
                System.out.println("-----Zonas Disponibles-----");
                System.out.println("---------------------------");
                System.out.println("A - Zona VIP ($25.000)");
                System.out.println("B - Zona Platea ($15.000)");
                System.out.println("C - Zona General ($11.000)");
                System.out.println("---------------------------");
                
                //preguntar la cantidad de entradas
                while (true) {
                    System.out.println("");
                    System.out.println("Cuantas entradas desea adquirir? (Tope 5 entradas por usuario):");
                    if  (input.hasNextInt()) {
                        cantidadEntradas = input.nextInt();
                        if (cantidadEntradas >= 1 && cantidadEntradas <= 5) {
                            break;
                        } else if (cantidadEntradas == 0) {
                            System.out.println("");
                            System.out.println("Debe adquirir al menos una entrada.");
                        } else {
                            System.out.println("");
                            System.out.println("Lo sentimos. La cantidad indicada supera la cantidad permitida por usuario.");
                        }
                    } else {
                        System.out.println("Lo sentimos, no se pudo procesar. Por favor, indicar la cantidad en numero.");
                        System.out.println("");
                        input.next(); // Para que solo se ingrese numero
                    }
                }
                
                System.out.println("Ingrese la zona de su preferencia (A, B o C):");

                String zona = input.next().toUpperCase();
                int precioBase = 0;
                double descuentoTipoCliente = 0.0;
                double descuentoMultipleEntrada = 0.0;

                // Validación de zona
                if (zona.equals("A")) {
                    precioBase = 25000;
                } else if (zona.equals("B")) {
                    precioBase = 15000;
                } else if (zona.equals("C")) {
                    precioBase = 11000;
                } else {
                    System.out.println("");
                    System.out.println("Lo sentimos, su solicitud es invalida. Intente de nuevo...");
                    continue;
                }
                
                    System.out.println("");
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("-----------Le recordamos las promociones disponibles-----------");
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("-5% descuento a partir de 3 entradas (maximo 5 por usuario)");
                    System.out.println("-El descuento 'Estudiante' es a partir de 15 anios (Compra unitaria)");
                    System.out.println("-El descuento 'Tercera Edad' es a partir de 60 anios (Compra unitaria)");
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("---------------LAS PROMOCIONES NO SON ACUMULABLES--------------");                    
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("");

                // EDAD Y VALIDACION
                int edad = 0;
                while (true) {
                    System.out.println("");
                    System.out.println("Por favor, indique la edad: ");
                    if (input.hasNextInt()) {
                        edad = input.nextInt();

                        if (edad <= 0 || edad > 100) {
                            System.out.println("");
                            System.out.println("La edad ingresada no es valida. Intente de nuevo...");
                            
                        } else {
                            //Determina tipoCliente para aplicar descuento
                            String tipoCliente = "Normal";
                            if (cantidadEntradas == 1) {
                                if (edad >= 60) {
                                descuentoTipoCliente = 0.15; //Descuento compra unitaria
                                tipoCliente = "Tercera Edad";
                            } else if (edad >= 15 && edad <= 59) {
                                descuentoTipoCliente = 0.10;
                                tipoCliente = "Estudiante";
                            }
                        } else if (cantidadEntradas >= 3) {
                            descuentoTipoCliente = 0.05; //Descuento compra multiple
                            tipoCliente = "Compra Multiple"; 
                            } else {
                                descuentoTipoCliente = 0.0;
                                tipoCliente = "Normal";
                            }
                            break;
                        }
                    } else {
                        System.out.println("");
                        System.out.println("Por favor, ingrese un numero valido para la edad.");
                        input.next();
                    }
                }

                // Paso 3: Calcular el precio final
                double precioFinalUnitario = precioBase - (precioBase * descuentoTipoCliente);
                double precioFinalTotal = precioFinalUnitario * cantidadEntradas;

                // Paso 4: Determinar tipo de cliente
                String tipoCliente;
                if (edad >= 60) {
                    tipoCliente = "Tercera Edad";
                } else if (edad >= 15) {
                    tipoCliente = "Estudiante";
                } else {
                    tipoCliente = "Normal";
                }

                // Paso 5: Guardar la entrada (si hay espacio)
                if (contadorEntradas < 5) {
                    //Advertencia y resumen preliminar antes de cnfirmar compra
                    
                    System.out.println("");
                    System.out.println("Estas a punto de comprar " + (int) cantidadEntradas + " entrada(s) para la zona " + zona);
                    System.out.println("Costo: $" + (int) precioFinalTotal);
                    System.out.println("Tipo de Cliente: " + tipoCliente);
                    System.out.println("Desea continuar con la compra? SI / NO");
                    
                    String respuesta = input.next().toUpperCase();

                    while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
                        System.out.println("Lo sentimos, solicitud no es valida. Por favor, escriba SI o NO:");
                        respuesta = input.next().toUpperCase();
                    }

                    if (respuesta.equals("NO")) {
                        System.out.println("");
                        System.out.println("La compra ha sido cancelada. Lo redigiremos al Menu Principal");
                        System.out.println("");
                        continue; //Regresa al menu principal
                    }
                    
                    //Confirmar compra
                    for (int j = 0; j < cantidadEntradas && contadorEntradas < 5; j++) {
                        contadorEntradas++;
                        totalEntradasVendidas++;
                        totalIngresos += precioFinalUnitario;
                        
                        if (contadorEntradas == 1) {
                            entradaUno = contadorEntradas;
                            entrada1Ubic = zona;
                            entrada1Precio = precioFinalUnitario;
                            entrada1Tipo = tipoCliente;
                        } else if (contadorEntradas == 2) {
                            entradaDos = contadorEntradas;
                            entrada2Ubic = zona;
                            entrada2Precio = precioFinalUnitario;
                            entrada2Tipo = tipoCliente;
                        } else if (contadorEntradas == 3) {
                            entradaTres = contadorEntradas;
                            entrada3Ubic = zona;
                            entrada3Precio = precioFinalUnitario;
                            entrada3Tipo = tipoCliente;
                        } else if (contadorEntradas == 4) {
                            entradaCuatro = contadorEntradas;
                            entrada4Ubic = zona;
                            entrada4Precio = precioFinalUnitario;
                            entrada4Tipo = tipoCliente;
                        } else if (contadorEntradas == 5) {
                            entradaCinco = contadorEntradas;
                            entrada5Ubic = zona;
                            entrada5Precio = precioFinalUnitario;
                            entrada5Tipo = tipoCliente;
                        }
                    }
                    
                    
                    // Paso 6: Mostrar resumen
                    System.out.println("");
                    System.out.println("--------------------------------");
                    System.out.println("----- RESUMEN DE SU COMPRA -----");
                    System.out.println("--------------------------------");
                    System.out.println("Zona seleccionada: " + zona);
                    System.out.println("Tipo de cliente: " + tipoCliente);
                    System.out.println("Precio normal por entrada: $" + precioBase);
                    System.out.println("Cantidad de entradas: " + cantidadEntradas);
                    System.out.println("Promocion aplicada: " + (int) (descuentoTipoCliente * 100) + "%");
                    System.out.println("Precio final a pagar: $" + (int) precioFinalTotal);
                    System.out.println("-------------------------------");

                } else {
                    System.out.println("Ha alcanzado la cantidad maxima de entradas compradas!");
                }

                // Preguntar si desea regresar al menu principal
                System.out.println("");
                System.out.println("Desea volver al menu principal? SI / NO");

                String respuesta = input.next().toUpperCase();

                // Validar entrada
                while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
                    System.out.println("Lo sentimos, solicitud no es valida. Por favor, escriba SI o NO:");
                    respuesta = input.next().toUpperCase();
                }

                if (respuesta.equals("NO")) {
                    System.out.println("");
                    System.out.println("Muchas gracias por habernos visitado. Vuelva pronto!");
                    salir = true;
                }
            //OPCION 2    
            } else if (solicitud == 2) {
                System.out.println("");
                System.out.println("Ha seleccionado *Ver Promociones*");
                System.out.println("-------------------------------------");
                System.out.println("------ PROMOCIONES DISPONIBLES ------");
                System.out.println("-------------------------------------");
                System.out.println("Tenemos 5% de descuento a partir de 3 entradas");
                System.out.println("Estudiante (15 a 59 anios): 10% de descuento (Compra unitaria)");
                System.out.println("Tercera Edad (60 anios en adelante): 15% de descuento (Compra unitaria)");
                System.out.println("--------------------------------------");
                System.out.println("--LAS PROMOCIONES NO SON ACUMULABLES--");
                System.out.println("--------------------------------------");
            
            //OPCION 3  
            } else if (solicitud == 3) {
                System.out.println("");
                System.out.println("Ha seleccionado *Buscar Entrada Existente*");
                System.out.println("Ingrese la letra de la zona a buscar (a) VIP, (b) Platea o (c) General):");

                String zonaBuscar = input.next().toUpperCase();
                
                while (!(zonaBuscar.equals("A") || zonaBuscar.equals("B") || zonaBuscar.equals("C"))) {
                    System.out.println("Letra invalida. Elija A, B o C. Intente de nuevo:");    
                    zonaBuscar = input.next().toUpperCase();
                }
                
                // Mostrar los datos de la entrada buscada
                boolean coincidencia = false;
                System.out.println("");
                System.out.println("Revisando zona: " + zonaBuscar);
                System.out.println("--------------------------------");                     
                        
                        if (entradaUno != 0 && entrada1Ubic.equals(zonaBuscar)) {
                            coincidencia =true;
                            System.out.println("Entrada N°1 - Tipo " + entrada1Tipo + ", Precio: $" + (int) entrada1Precio);
                        }
                        if (entradaDos != 0 && entrada2Ubic.equals(zonaBuscar)) {
                            coincidencia =true;
                            System.out.println("Entrada N°2 - Tipo " + entrada2Tipo + ", Precio: $" + (int) entrada2Precio);
                        }
                        if (entradaTres != 0 && entrada3Ubic.equals(zonaBuscar)) {
                            coincidencia =true;
                            System.out.println("Entrada N°3 - Tipo " + entrada3Tipo + ", Precio: $" + (int) entrada3Precio);
                        }
                        if (entradaCuatro != 0 && entrada4Ubic.equals(zonaBuscar)) {
                            coincidencia =true;
                            System.out.println("Entrada N°4 - Tipo " + entrada4Tipo + ", Precio: $" + (int) entrada4Precio);
                        }
                        if (entradaCinco != 0 && entrada5Ubic.equals(zonaBuscar)) {
                            coincidencia =true;
                            System.out.println("Entrada N°5 - Tipo " + entrada5Tipo + ", Precio: $" + (int) entrada5Precio);
                        }
                        if (!coincidencia) {
                            System.out.println("");
                            System.out.println("Lo sentimos. No se ha realizado una compra para esa zona. Será redirigido al menu principal...");
                        }
                        
            //OPCION 4    
            } else if (solicitud == 4) {
                System.out.println("");
                System.out.println("Ha seleccionado *Eliminar Entrada*");
                System.out.println("Ingrese la cantidad de entradas que desea eliminar (del 1 al 5):");

                int numeroEliminar = 0;

                while (true) {
                    if (input.hasNextInt()) {
                        numeroEliminar = input.nextInt();
                        if (numeroEliminar >= 1 && numeroEliminar <= 5) {
                            break;
                        } else {
                            System.out.println("");
                            System.out.println("Numero invalido. Debe estar entre 1 y 5. Intente de nuevo:");
                        }
                    } else {
                        System.out.println("");
                        System.out.println("La solicitud no es valida. Ingrese un numero:");
                        input.next();
                    }
                }

                boolean eliminada = false;

                switch (numeroEliminar) {
                    case 1:
                        if (entradaUno != 0) {
                            totalIngresos -= entrada1Precio;
                            totalEntradasVendidas--;
                            entradaUno = 0;
                            entrada1Ubic = null;
                            entrada1Tipo = null;
                            entrada1Precio = 0;
                            eliminada = true;
                        }
                        break;
                    case 2:
                        if (entradaDos != 0) {
                            totalIngresos -= entrada2Precio;
                            totalEntradasVendidas--;
                            entradaDos = 0;
                            entrada2Ubic = null;
                            entrada2Tipo = null;
                            entrada2Precio = 0;
                            eliminada = true;
                        }
                        break;
                    case 3:
                        if (entradaTres != 0) {
                            totalIngresos -= entrada3Precio;
                            totalEntradasVendidas--;
                            entradaTres = 0;
                            entrada3Ubic = null;
                            entrada3Tipo = null;
                            entrada3Precio = 0;
                            eliminada = true;
                        }
                        break;
                    case 4:
                        if (entradaCuatro != 0) {
                            totalIngresos -= entrada4Precio;
                            totalEntradasVendidas--;
                            entradaCuatro = 0;
                            entrada4Ubic = null;
                            entrada4Tipo = null;
                            entrada4Precio = 0;
                            eliminada = true;
                        }
                        break;
                    case 5:
                        if (entradaCinco != 0) {
                            totalIngresos -= entrada5Precio;
                            totalEntradasVendidas--;
                            entradaCinco = 0;
                            entrada5Ubic = null;
                            entrada5Tipo = null;
                            entrada5Precio = 0;
                            eliminada = true;
                        }
                        break;
                }

                if (eliminada) {
                    System.out.println("");
                    System.out.println("Entrada eliminada correctamente.");
                } else {
                    System.out.println("");
                    System.out.println("No existe una entrada registrada con ese numero.");
                }
            
            } else if (solicitud == 5) {
            System.out.println("");
            System.out.println("Muchas gracias por su visita. Hasta pronto!");
            salir = true; 
        }

            System.out.println("");
        }

        System.out.println("-----------------------------");
        System.out.println("------- RESUMEN FINAL -------");
        System.out.println("Entradas vendidas: " + totalEntradasVendidas);
        System.out.println("Total ingresos: $" + (int) totalIngresos);
        System.out.println("-----------------------------");

        input.close();
    }
}