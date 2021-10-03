package pobj.asciiart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ChargeurImage {
	public static ImageBrute charge(String nom) throws ImageException {
		Scanner sc;
		// erreur si le fichier n’existe pas5sc =newScanner(newFile(nom));
		try {
			sc =new Scanner(new File(nom));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ImageException("BadImageFileName");
		}
		LinkedList<char[]> lignes =new LinkedList<char[]>();
		while(sc.hasNextLine()) {
			lignes.add(sc.nextLine().toCharArray());
			if(lignes.getFirst().length != lignes.getLast().length) {
				sc.close();
				throw new ImageException("BadImageLineSize");
			}
		}
		sc.close();
		
		if(lignes.isEmpty()) {
			throw new ImageException("EmptyImageFile");
		}
		
		char[][] pixels = lignes.toArray(new char[0][0]);
		return new ImageBrute(pixels);
	}
}
