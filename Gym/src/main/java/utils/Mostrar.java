package utils;

public class Mostrar {

	public static void menu() {
		System.out.println();
		System.out.println(" 1 - Entrada usuaris (CREATE)");
		System.out.println(" 2 - Llistar usuaris (READ)");
		System.out.println(" 3 - Actualitzar dades (UPDATE)");
		System.out.println(" 4 - Eliminar usuari (DELETE)");
		System.out.println(" 0 - Sortir");
	}

	public static void mostraRead() {
		System.out.println("*************************************************");
		System.out.println("   **  MOSTRA ELS ALUMNES MATRICULATS  **");
		System.out.println("*************************************************");
		
	}

	public static void mostraCreate() {
		System.out.println("*************************************************");
		System.out.println("   **  INTRODUEIX LES DADES DEL NOU ALUMNE  **");
		System.out.println("*************************************************");
	}
	
	public static void mostraUpdate() {
		System.out.println("*************************************************");
		System.out.println("          **  MODIFICA ALUMNE  **");
		System.out.println("*************************************************");
		System.out.println("Quin usuari vols modificar?(Introdueix el DNI)");
	}
	
	public static void mostrDelete() {
		System.out.println("*************************************************");
		System.out.println("          **  ELIMINA ALUMNE  **");
		System.out.println("*************************************************");
		System.out.println("Quin usuari vols eliminar?(Introdueix el DNI)");
	}
	
	public static void intro() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("  ***********************************************************");
		System.out.println("    ********* || WELCOME TO THE BLACK TEAM GYM || *********");
		System.out.println("  ***********************************************************");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
