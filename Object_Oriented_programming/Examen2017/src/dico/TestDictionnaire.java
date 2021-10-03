package dico;

import java.util.Arrays;

public class TestDictionnaire {
	public static void main(String[] args) {
		DictionnaireComplet dico = new DictionnaireComplet(Arrays.asList("chaise","table"), Arrays.asList("Charlemagne","Turing"));
		for (String w: dico) {
			System.out.println(w);
		}
	}
}
/** trace attendue: 
chaise
table
Charlemagne
Turing
**/