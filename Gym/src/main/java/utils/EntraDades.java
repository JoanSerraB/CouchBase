package utils;

import java.util.Scanner;

public class EntraDades {

	public static String demanarString(String titol) {
		System.out.println(titol);
		Scanner lector = new Scanner(System.in);
		String st = lector.nextLine();
		return st;
	}

	public static int demanarEnter(String titol) {
		int i = 0;
		boolean found = false;
		System.out.println(titol);
		Scanner lector = new Scanner(System.in);
		while (!found) {
			if (lector.hasNextInt()) {
				i = lector.nextInt();
				found = true;
			} else {
				System.out.println(("Entra un enter valid"));
				lector.next();
			}
		}
		return i;
	}

	public static boolean sN() {
		boolean surt = false;
		while(!surt) {
			String lletra = EntraDades.demanarString("");
			if (lletra.equalsIgnoreCase("s")) {
				return true;
			} else if (lletra.equalsIgnoreCase("n")) {
				return false;
			} else {
				System.out.println("Has d'introduir 's' o 'n'");			
			}			
		}
		return false;
	}
}
