package Tuto3;


import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Facturation extends Application{

	private Facture achats = new Facture();
	
	
	public static void main(String args[]) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		//il faut déployer les éléments du plus profond au moins profond
		
		//nom des colonnes
		Label nomlabel=new Label("Nom");
		Label prixunitairelabel=new Label("Prix Unitaire");
		Label quantitelabel=new Label("Quantité");
		Label prixtotallabel=new Label("Prix Total");
		
		//Partie haute
		//type positionnement en grille
		GridPane grille=new GridPane();
		
		// ajout des label dans la grille
		grille.add(nomlabel, 0, 0);
		grille.add(prixunitairelabel, 1, 0);
		grille.add(quantitelabel, 2, 0);
		grille.add(prixtotallabel, 3, 0);
		
		// Partie basse avec la manipulation
		HBox hbox=new HBox();
		
		Button ajouterbutton=new Button("Ajouter");
		
		
		
		Label totalfacturelabel=new Label("0");
		

		
		hbox.getChildren().add(ajouterbutton);
		hbox.getChildren().add(totalfacturelabel);
		
		
		
		
		// ajouter une action pour le bouton
		ajouterbutton.setOnAction(e -> {
		//-------------------------------------------------------------------------------	
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
			
			// Ajout du nouvel achat
			Achat nouvelachat=new Achat("Nom", 0.0, 1);
			achats.addAchat(nouvelachat);
			
			
			//binding
			nomfield.textProperty().bindBidirectional(nouvelachat.getNom());
			prixunitairefield.textProperty().bindBidirectional(nouvelachat.
					getPrixUnitaire(), new NumberStringConverter());
			quantitespinner.getValueFactory().valueProperty().bindBidirectional(nouvelachat
					.getQuantite());
			prixtotalvallabel.textProperty().bind(nouvelachat.getPrixTotal().
					asString());
			
			
		
			
			//ajout d'actions sur quantité et prix unitaire afin de pouvoir calculer le prix total
			// ils sont appelés handlers
			nomfield.textProperty().addListener((observable, oldval, newval)-> {
				nouvelachat.setNom(newval);
			});
			
			prixunitairefield.textProperty().addListener(
					(observable, oldval, newval) -> {
					nouvelachat.setPrixUnitaire(Double.parseDouble(newval));
					
					prixtotalvallabel.setText(
					// on doit faire un parse à newval car le type textfield est un string 
					// pas nécessaire pour quantité car c'est déja de type double
							nouvelachat.getPrixTotal()+""
					);
					totalfacturelabel.setText(achats.getPrixTotal()+"");
					}
					);
					quantitespinner.valueProperty().addListener(
					(observable, oldval, newval) -> {
					nouvelachat.setQuantite(newval);
					prixtotalvallabel.setText(nouvelachat.getPrixTotal()+"");
					totalfacturelabel.setText(achats.getPrixTotal()+"");
					}
					);
					
					totalfacturelabel.textProperty().bind(achats.getPrixTotal().asString());
		//------------------------------------------------------------------------------	
		});
		
		
		
		
		
		
		//Element racine
		VBox root=new VBox();
		root.getChildren().addAll(grille, hbox);
		
		Scene scene=new Scene(root, 1000,800 );
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Facture");
		primaryStage.show();
		
		
		//Amélioration de l'affichage
		totalfacturelabel.setAlignment(Pos.BOTTOM_RIGHT);
		grille.setPadding(new Insets(20, 20, 20, 20));
		grille.setHgap(10);
		grille.setVgap(15);
		
		
		hbox.setPadding(new Insets(20, 20, 20, 20));
		hbox.setSpacing(20);
		
		
		//centrer tous les composants dans la fenêtre
		grille.setAlignment(Pos.CENTER); // grille
		root.setAlignment(Pos.CENTER);   // vertical box
		hbox.setAlignment(Pos.CENTER);   // horizontal box 
		
	}

}
