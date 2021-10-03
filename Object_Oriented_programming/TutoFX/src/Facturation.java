

import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Facturation extends Application{

	private List<AchatView> achats = new LinkedList<>();
	
	
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
		achats.add(new AchatView(grille, totalfacturelabel));
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
