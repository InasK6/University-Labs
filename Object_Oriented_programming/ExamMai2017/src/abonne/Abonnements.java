package abonne;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class Abonnements implements IAbonnements {
	private Map<String, Set<String>> abonnes;
	private Map<String, Set<String>> abonnements;
	
	public Abonnements() {
		abonnes=new HashMap<String, Set<String>>();
		abonnements=new HashMap<String, Set<String>>();
	}
	@Override
	//La relation est réciproque donc je teste la présence dans une seule map
	//et je suppose que si elle est présente dans l'une elle est présente dans l'autre aussi
	public boolean addAbonnement(String suivi, String suiveur) {
		// TODO Auto-generated method stub
		if(abonnes.containsKey(suivi)) {
			if (abonnes.get(suivi).contains(suiveur))
				return false;
			else {
				abonnes.get(suivi).add(suiveur);
			}
		}
		else {
			Set<String> set= new HashSet<String>();
			set.add(suiveur);
			abonnes.put(suivi, set);
		}
		if(abonnements.containsKey(suiveur)) {
			abonnements.get(suiveur).add(suivi);
		}
		else {
			Set<String> set= new HashSet<String>();
			set.add(suivi);
			abonnements.put(suiveur, set);
		}
		return true;
	}

	@Override
	public boolean remAbonnement(String suivi, String suiveur) {
		// TODO Auto-generated method stub
		// si suiveur n'est abonné à personne
		if(!abonnements.containsKey(suiveur)) {
			System.out.println("1");
			return false;
		}
		// si suiveur n'est pas abonné à suivi
		if(!abonnements.get(suiveur).contains(suivi)) {
			System.out.println("2");
			return false;
		}
		// relation supposé réciproque pas de test inverse
		// faute à l'utilisateur
		abonnements.get(suiveur).remove(suivi);
		if(abonnements.get(suiveur).isEmpty()) {
			abonnements.remove(suiveur);
		}
		//mise à jour de la deuxième table 
		abonnes.get(suivi).remove(suiveur);
		if(abonnes.get(suivi).isEmpty()) {
			abonnes.remove(suivi);
		}
		return true;
	}

	@Override
	public Collection<String> getAbonnes(String suivi) {
		// TODO Auto-generated method stub
		if(abonnes.get(suivi)==null) {
			return Collections.EMPTY_SET;
		}
		return abonnes.get(suivi);
	}

	@Override
	public Collection<String> getAbonnements(String suiveur) {
		// TODO Auto-generated method stub
		if(abonnements.get(suiveur)==null) {
			return Collections.EMPTY_SET;
		}
		return abonnements.get(suiveur);
	}

	@Override
	public Collection<String> getAllSuivis() {
		// TODO Auto-generated method stub
		if(abonnes.isEmpty()) {
			return Collections.EMPTY_SET;
		}
		return abonnes.keySet();
	}

	@Override
	public Collection<String> getAllSuiveurs() {
		// TODO Auto-generated method stub
		if(abonnements.isEmpty()) {
			return Collections.EMPTY_SET;
		}
		return abonnements.keySet();
	}

}
