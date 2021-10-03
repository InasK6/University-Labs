package abonne;

import java.io.File;
import java.util.Scanner;

public class ChargeurFichier {
	
	public static int charge(String nom, IAbonnements abos) throws AbonnementException {
		Scanner sc;
		// erreur si le fichier n'existe pas : lève une IOException.
		// TODO : convertir en BadAbonnementFileName
		sc = new Scanner(new File(nom));
		
		int done = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String [] words = line.trim().split(":");
			if (words.length != 2) {
				sc.close();
				// erreur : format de fichier incorrect
				// TODO : lever BadAbonnementFormat
				
			} else {
				abos.addAbonnement(words[0], words[1]);
				done++;
			}			
		}		
		sc.close();
		if (done == 0) {
			// erreur : fichier vide
			// TODO : lever EmptyAbonnementFile
		}
		return done;
	}
	
	public static void main(String[] args) {
		try {
			IAbonnements a = new Abonnements();
			int taille = charge("abo.txt", a);
			System.out.println("Lu : "+ taille);
		} catch (BadAbonnementFileName e) {
			System.err.println("Mauvais fichier !");
		} catch (BadAbonnementFormat e) {
			System.err.println("Mauvais format !");
		} catch (EmptyAbonnementFile e) {
			System.err.println("Fichier vide !");
		} catch (AbonnementException e) {
			e.printStackTrace();
		}
	}
}