package BlackTeam.provaGym;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import utils.CRUD;
import utils.EntraDades;
import utils.Mostrar;

public class App {

	public static void main(String[] args) {
		System.out.println("----------------  Cargandooooo.... ---------------");
		opcio();
		System.out.println("****************************************************");
		System.out.println("********************* ADEU !!! *********************");
	}

	public static void opcio() {

		// Iniciem la conexió
		Cluster cluster = CouchbaseCluster.create("localhost");
		cluster.authenticate("Administrator", "admintoor");
		Bucket bucket = cluster.openBucket("Black_Team_Gym");

		// Create a N1QL Primary Index (but ignore if it exists)
		bucket.bucketManager().createN1qlPrimaryIndex(true, false);
		Mostrar.intro();
		int opcio = 0;
		boolean finalitzar = false;
		do {
			Mostrar.menu();
			opcio = EntraDades.demanarEnter("Tria l'opcio");
			switch (opcio) {
			case 1:
				Mostrar.mostraCreate();
				CRUD.create(bucket);
				break;
			case 2:
				Mostrar.mostraRead();
				CRUD.read(bucket, 2, "");
				System.out.println();
				System.out.println(
						"Vols veure els alumnes matriculats d'algun grup? [ Hapkido  -  karate  -   Muay Thai ]");
				System.out.println("Selecciona 'S/N'...");
				boolean sN = EntraDades.sN();
				if (sN) {
					System.out.println("Indica la disciplina a visualitzar [ Hapkido  -  karate  -   Muay Thai ]");
					String tria = EntraDades.demanarString("");
					CRUD.read(bucket, 1, tria);
				}
				break;
			case 3:
				Mostrar.mostraUpdate();
				CRUD.update(bucket);
				break;
			case 4:
				Mostrar.mostrDelete();
				CRUD.delete(bucket);
				break;
			case 0:
				cluster.disconnect();
				finalitzar = true;
				break;
			default:
				System.out.println("Valor introduit incorrecte.");
				finalitzar = false;
				break;
			}
		} while (!finalitzar);
	}
}
