package fase3;

import java.io.IOException;
import java.util.Scanner;

import componentesDiccionario.Diccionario;
import componentesDiccionario.HashMapPalabras;
import componentesInternet.Internet;

public class AplicacionWeb {

	public static void main(String[] args) throws IOException {
		
		Internet internet = Internet.getInstance();
		internet.inicializar("./data/index", "./data/pld-arc");
		
		HashMapPalabras hm = new HashMapPalabras();
		Diccionario diccionario = Diccionario.getInstance();
		diccionario.setDiccionario(hm);
		
		diccionario.inicializar("./data/wordsshuffle.txt");
		
		System.out.println();
		
		int opcion = 1;
		Scanner sc = new Scanner(System.in);
		String pal;
		while(opcion!=0) {
			System.out.println("Que deseas hacer?");
			System.out.println("1. Buscar webs por palabra clave");
			System.out.println("2. Comprobar si dos Web's estan conectadas");
			System.out.println("3. Imprimir camino mas corto entre dos Webs");
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
				case 2:
					System.out.print("URL de origen: ");
					String url12 = sc.nextLine();
					System.out.print("URL de destino: ");
					String url22 = sc.nextLine();
					System.out.println("\n-----------------------");
					System.out.println("Estan conectados: " + internet.estanConectados(url12, url22));
					System.out.println("-----------------------\n");
					break;
				case 3:
					System.out.print("URL de origen: ");
					String url13 = sc.nextLine();
					System.out.print("URL de destino: ");
					String url23 = sc.nextLine();
					System.out.println("\n-----------------------------------------------------------");
					System.out.println("Camino mas corto entre "+url13+" y "+url23+":\n");
					internet.imprimirCamino(url13, url23);
					System.out.println("-----------------------------------------------------------"+"\n");
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