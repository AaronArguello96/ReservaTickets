import java.util.Scanner;
/**
 * Reserva de tickets
 * @author Aarón Argüello Villar
 */
public class ReservaTickets {

	//GLOBAL
	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		reservatickets();
	}
	
	public static void reservatickets() {
			
		//VARIABLES
		char vacio = 'L';
		char reservado = 'R';
		char vagon[][];
		vagon = new char[6][6];
		int opcion; //1: elegir asiento, 2: asignar primer asiento libre
		int fila, columna;
		boolean posicionValida, fin = true;
		
		//Cargamos el vagon vacio
		CargarVagon(vagon, vacio);
		System.out.println("*******BIENVENIDO AL PROGRAMA DE RESERVA DE TICKETS*******");
		System.out.println("Tiene dos opciones:");
		System.out.println("Opción 1: Elije que asiento quiere reservar.");
		System.out.println("Opción 2: El programa elije el primer asiento libre que encuentre.");
		
		while(fin) {
			
			//Pedimos fila y columna al jugador
			opcion = PedirEntero("Elija una opción: ");
			//Comprobamos el valor introducido por el usuario
			switch(opcion) {
				
				//Si es 1 es el caso en el que puede elegir el asiento que quiera
				case 1: 
					System.out.println("Ha elegido la opción 1.");
					//Lo mostramos
					MostrarVagon(vagon);
					
					//Pedimos fila y columna al usuario
					fila = PedirEntero("Escribe la fila: ");
					columna = PedirEntero("Escribe la columna: ");
					
					//Comprobamos si la posición es válida
					posicionValida = ValidarPosicion(vagon, fila, columna);
					if(posicionValida) {
						if(!PosicionOcupada(vagon, fila, columna, vacio)) {
							
								ElegirAsiento(vagon, fila, columna, reservado);
								//Lo mostramos
								MostrarVagon(vagon);
								System.out.print("Ha reservado asiento en la fila: " + fila);						
								System.out.println(" y en la columna: " + columna);
							
							
						}else {
							System.out.println("La posición está ocupada.");
						}			
					}else {
						System.out.println("La posición no es válida.");
					}
					
					break;
					
				//Si es 2 se le asigna el primer asiento libre encontrado
				case 2: 
					System.out.println("Ha elegido la opción 2.");
					//Lo mostramos
					MostrarVagon(vagon);
					AsignarAsientoLibre(vagon, reservado);
					System.out.println("");
					MostrarVagon(vagon);
					break;
				//Si no es ninguna de las opciones se mostrará el menaje de que no es válido
				default:
					System.out.println("¡Opción no válida!");
			}	
		}	
	}
	
	//Método que carga el vagon vacío
	public static void CargarVagon(char[][] matriz, char simbolo) {
		for(int i=0; i<matriz.length;i++) {
			for(int j=0; j<matriz.length;j++) {
				matriz[i][j] = simbolo;
			}
		}
	}
	
	//Método que muestra el vagon
	public static void MostrarVagon(char[][] matriz) {
		for(int i=0; i<matriz.length;i++) {
			for(int j=0; j<matriz[0].length;j++) {
				System.out.print(matriz[i][j] + "");
			}
			System.out.println("");
		}
	}
	
	//Método que pedirá un dato al usuario
	public static int PedirEntero(String mensaje) {
		System.out.println(mensaje);
		int numero = teclado.nextInt();
		return numero;
	}
	
	//Método que comprueba si la posición elegida por el jugador es válida
	public static boolean ValidarPosicion(char [][] tablero, int fila, int columna) {
		if(fila>=0 && fila<tablero.length && columna >=0 && columna<tablero.length) {
			return true;
		}
		return false;
	}
	
	//Método que comprueba si la casilla está ya ocupada
	public static boolean PosicionOcupada(char[][]tablero, int fila, int columna, char simboloDef) {
		if(tablero[fila][columna] != simboloDef) {
			return true;
		}
		return false;
	}
	
	//Método que trabaja con la elección del asiento por parte del usuario
	public static void ElegirAsiento(char [][] tablero, int fila, int columna, char simbolo) {
		tablero[fila][columna] = simbolo;
	}
	
	//Método que trabaja con la elección del primer asiento libre
	public static void AsignarAsientoLibre(char [][] matriz, char simbolo) {
		for(int i=0; i<matriz.length;i++) {
			for(int j=0; j<matriz[0].length;j++) {
				if(matriz[i][j] != simbolo) {
					matriz[i][j] = simbolo;		
					System.out.print("Se ha reservado sitio en la fila: " + i);
					System.out.println(" y en la columna: " + j);
					return;		
				}
			}
		}	
	}
}
