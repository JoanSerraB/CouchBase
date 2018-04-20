package utils;

import java.util.ArrayList;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;

public class CRUD {

	// CREATE
	public static void create(Bucket bucket) {

		String nom;
		String dni;
		String[] arts = new String[3];
		boolean sN;
		int edat = 0;

		nom = EntraDades.demanarString("Entra el nom: ");
		edat = EntraDades.demanarEnter("Introdueix edat: ");
		dni = EntraDades.demanarString("Entra el DNI: ");
		System.out.println("Art marcial [ Hapkido  -  karate  -   Muay Thai ]");

		for (int i = 0; i < arts.length; i++) {
			arts[i] = EntraDades.demanarString("");
			System.out.println("Vols matricular-te d'un altre art marcial? (S/N)");
			sN = EntraDades.sN();
			if (!sN) {
				break;
			}
		}

		// Crea un JSON Document
		JsonObject person = JsonObject.create().put("name", nom).put("edat", edat).put("dni", dni).put("arts",
				JsonArray.from(arts[0], arts[1], arts[2]));

		// Store the Document
		bucket.insert(JsonDocument.create(dni, person));

	}

	// READ
	public static void read(Bucket bucket, int select, String tria) {
		N1qlQueryResult nom = null, dni = null;

		ArrayList<String> nomList = new ArrayList<String>();
		ArrayList<String> dniList = new ArrayList<String>();

		if (select == 1) {			
			nom = bucket.query(
					N1qlQuery.parameterized("SELECT name FROM Black_Team_Gym WHERE $1 IN arts", JsonArray.from(tria)));
			dni = bucket.query(
					N1qlQuery.parameterized("SELECT dni FROM Black_Team_Gym WHERE $1 IN arts", JsonArray.from(tria)));
		} else if (select == 2) {
			nom = bucket.query(N1qlQuery.simple("SELECT name FROM Black_Team_Gym"));
			dni = bucket.query(N1qlQuery.simple("SELECT dni FROM Black_Team_Gym"));
		}

		for (N1qlQueryRow row : nom) {

			String raw = new String(row.toString());
			int index = raw.indexOf(":");
			String name = raw.substring(index + 2, raw.length() - 2);

			nomList.add(name);

		}

		for (N1qlQueryRow row : dni) {

			String raw = new String(row.toString());
			int index = raw.indexOf(":");
			String Dni = raw.substring(index + 1, raw.length() - 1).replaceAll("\"", "");

			dniList.add(Dni);

		}

		for (int i = 0; i < dniList.size(); i++) {
			System.out.println("Alumne: " + nomList.get(i) + "  -   DNI: " + dniList.get(i));

		}

	}

	// DELETE
	public static void delete(Bucket bucket) {
		
		read(bucket, 2, "");
		String elimina = EntraDades.demanarString("Selecciona...");
		bucket.remove(elimina);
		System.out.println("Usuari eliminat correctament");
	}

	// UPDATE
	public static void update(Bucket bucket) {
		
		read(bucket, 2, "");
		String dni = EntraDades.demanarString("");
		String nom = EntraDades.demanarString("Escriu el nom a modificar");
		JsonDocument doc = bucket.get(dni);
		JsonObject content = doc.content().put("name", nom);
		bucket.replace(JsonDocument.create(dni, content));
		System.out.println("Actualitzat correctament...");
	}
}
