package dico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dictionnaire {
	protected List<String> motsCommuns ;
	protected List<String> nomsPropres ;
	
	public Dictionnaire(List<String> motsCommuns, List<String> nomsPropres) {		
		this.motsCommuns = new ArrayList<String>(motsCommuns);
		this.nomsPropres = new ArrayList<String>(nomsPropres);
	}
	public List<String> getMotsCommuns() {
		return motsCommuns;
	}
	public List<String> getNomsPropres() {
		return nomsPropres;
	}

	
	

}
