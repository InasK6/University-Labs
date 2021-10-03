

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AchatView {
	public AchatView(GridPane grille, Label totalfacturelabel ) {
		//on récupère le nombre de lignes déja présentes dans la grille
		int ligne = grille.getRowCount();
		//on crée de nouveaux champs textes
		TextField nomfield = new TextField("Nom");
		TextField prixunitairefield = new TextField("0");
		// création d'un champ spinner
		Spinner<Integer> quantitespinner = new Spinner<Integer>(0, 50, 1);
		// création d'un label pour le prix total
		Label prixtotalvallabel = new Label("0");
		//ajout de tous les éléments aux endroits correspondant dans la grille
		grille.add(nomfield, 0, ligne);
		grille.add(prixunitairefield, 1, ligne);
		grille.add(quantitespinner, 2, ligne);
		grille.add(prixtotalvallabel, 3, ligne);
		
		//ajout d'actions sur quantité et prix unitaire afin de pouvoir calculer le prix total
		// ils sont appelés handlers
		prixunitairefield.textProperty().addListener(
				(observable, oldval, newval) -> {
				prixtotalvallabel.setText(
				// on doit faire un parse à newval car le type textfield est un string 
				// pas nécessaire pour quantité car c'est déja de type double
				Double.parseDouble(newval) * quantitespinner.getValue() + ""
				);
				}
				);
				quantitespinner.valueProperty().addListener(
				(observable, oldval, newval) -> {
				prixtotalvallabel.setText(
				newval * Double.parseDouble(prixunitairefield.getText()) + ""
				);
				}
				);

				prixtotalvallabel.textProperty().addListener((observable, oldval, newval) -> {
					double ancientotal = Double.parseDouble(totalfacturelabel.getText());
					double nouveautotal = ancientotal - Double.parseDouble(oldval) + Double.
					parseDouble(newval);
					totalfacturelabel.setText(nouveautotal + "");
					}
					);
					
				
	}
}
