package fase2;

import java.io.IOException;
import java.util.Scanner;

import componentesDiccionario.ABBPalabras;
import componentesDiccionario.Diccionario;
import componentesInternet.Internet;

public class AplicacionWeb {

	public AplicacionWeb() {
		
	}

	public static void main(String[] args) throws IOException {
		
		Internet internet = Internet.getInstance();
		internet.inicializar("./data/index", "./data/pld-arc");
		
		ABBPalabras arbolDeNavidad = new ABBPalabras();
		Diccionario diccionario = Diccionario.getInstance();
		diccionario.setDiccionario(arbolDeNavidad);
		
		diccionario.inicializar("./data/wordsshuffle.txt");
		
		arbolDeNavidad.filtrarPalabrasClave();
		
		System.out.println();
		
		int opcion = 1;
		Scanner sc = new Scanner(System.in);
		String pal;
		while(opcion!=0) {
			System.out.println("Que deseas hacer?");
			System.out.println("1. Buscar webs por palabra clave");
			System.out.println("0. Salir\n");
			System.out.print("Seleccion: ");
			try {
				opcion = Integer.parseInt(sc.nextLine());
				switch(opcion) {
				case 1: 
					System.out.print("\nIntroduce una palabra: ");
					pal = sc.nextLine();
					System.out.println("\n------RESULTADOS------");
					internet.buscadorWeb(pal);
					System.out.println("----------------------"+"\n");
					break;
				default: 
					break;
				}
			} catch(NumberFormatException e) {
				System.out.println("El valor introducido debe ser 0/1.\n");
			}
		}
		sc.close();

	}

}