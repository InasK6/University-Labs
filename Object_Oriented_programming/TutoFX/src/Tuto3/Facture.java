package Tuto3;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Facture {
	private List<Achat> achats = new LinkedList<>();
	private final DoubleProperty prixtotalProperty=new SimpleDoubleProperty();
	public void addAchat(Achat achat) {
		//S'abonner au prix total au moment de l'ajout d'un achat
		achats.add(achat);
		achat.getPrixTotal().addListener((obs, old, n)->{
		double v=prixtotalProperty.get();
		prixtotalProperty.set(v-old.doubleValue()+n.doubleValue());
	});
	}
	public DoubleProperty getPrixTotal() {
		/*tuto 3
		 * double res = 0.0;
		for (Achat a : achats) {
			res += a.getPrixTotal();
		}
		return res;*/
		return prixtotalProperty;
	}
}
