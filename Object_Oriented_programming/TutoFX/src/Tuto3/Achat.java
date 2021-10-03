package Tuto3;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Achat {
private final StringProperty nom;
private final DoubleProperty prixUnitaire;
private final ObjectProperty<Integer> quantite;

private final DoubleProperty prixtotal;

public Achat(String nom, double prixUnitaire, int quantite) {
this.nom = new SimpleStringProperty(nom);
this.prixUnitaire = new SimpleDoubleProperty(prixUnitaire);
this.quantite = new SimpleObjectProperty<Integer>(quantite);
this.prixtotal=new SimpleDoubleProperty();

// tuto4: ajout d'observateurs afin de mettre à jour l'affichage en cas de changements des valeurs par l'application
InvalidationListener l=new InvalidationListener() {
	@Override
	public void invalidated(Observable observable) {
		prixtotal.set(Achat.this.getQuantite().get()*Achat.this.getPrixUnitaire().get());
	}
};
this.quantite.addListener(l);
this.prixUnitaire.addListener(l);
/*
 * ou
 
 
prixtotal.bind(IntegerProperty.integerProperty(this.quantite).multiply(this.prixUnitaire));
*/
}
public DoubleProperty getPrixTotal(){ return prixtotal;}
public StringProperty getNom(){ return nom;}
public void setNom(String nom){this.nom.set(nom); }
public DoubleProperty getPrixUnitaire(){return prixUnitaire;}
public void setPrixUnitaire(double prixUnitaire){this.prixUnitaire.set(
prixUnitaire);}
public ObjectProperty<Integer> getQuantite(){return quantite;}
public void setQuantite(int quantite){this.quantite.set(quantite);}
}