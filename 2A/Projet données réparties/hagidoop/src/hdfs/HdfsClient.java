package hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class HdfsClient {
	

	/* nombre de noeuds du cluster */
	private static int nbNodes = 3;
	private static int ports[] = {8080, 8081, 8082};
	private static String hosts[] = {"localhost", "localhost", "localhost"};

	private static void usage() {
		System.out.println("Usage: java HdfsClient read <file>");
		System.out.println("Usage: java HdfsClient write <txt|kv> <file>");
		System.out.println("Usage: java HdfsClient delete <file>");
	}
	
	/* Supprimer les fragments du fichier fname dans HDFS */
	public static void HdfsDelete(String fname) {
	}
	
	public static void HdfsWrite(int fmt, String fname) {

		byte[] buffer;
		int nbFragments;
		long fileSize;
		String filePath;
		File f;
		long sizeFragment;

		// le path du fichier à lire
		filePath = "\\data\\" + fname;
		f = new File(filePath);

		// Vérification de l'existence du fichier
		if (!f.exists()) {
			System.out.println("Le fichier n'existe pas");
			return;
		}

		// Taille du fichier
		fileSize = f.length();

		// Nombre de fragments
		nbFragments = (int) Math.ceil((double) fileSize / (double) nbNodes);
		sizeFragment = fileSize / nbFragments;

		// lire le ficchier et affecter chaque fragment à un noeud
		try {
			FileInputStream fis = new FileInputStream(f);
			for (int i = 0; i < nbFragments; i++) {
				while (sizeFragment > 0) {
					// Lire le fichier à l'aide de la fonction read
					fis.read(buffer);
					
					//  envoyer les fragments pour stockage sur les différentes machines.

				}

			}
		}
		for (int i = 0; i < nbFragments; i++) {

			// Création du nom du fragment
			String fragmentName = fname + "-" + i;

			// Création du buffer correspondant à la taille de chaque fragement (sauf le dernier) < 64Mo
			buffer = new byte[nbFragments < 64 * 1024 * 1024 ? nbFragments : 64 * 1024 * 1024];

			// Lire le fichier à l'aide de la fonction read
		
	}
}