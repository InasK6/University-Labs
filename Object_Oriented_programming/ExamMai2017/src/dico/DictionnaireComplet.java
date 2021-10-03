package dico;

import java.util.Iterator;
import java.util.List;

public class DictionnaireComplet extends Dictionnaire implements Iterable<String>{
	public DictionnaireComplet(List<String> motsCommuns, List<String> nomsPropres) {
		super(motsCommuns, nomsPropres);
	}
	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return Iterators.concat(motsCommuns.iterator(), nomsPropres.iterator());
	}
}
