package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchWindow extends Application{
	FXMLLoader loader;
	Scene scene;
	Stage stage;
	//launch main window
	public void start(Stage primaryStage)  { 		
		display(primaryStage, "Main Menu", "MainMenu.fxml");
	}
	
	public void display (Stage stage, String title, String filename) {
		FXMLLoader loader;
		Scene scene;
		if (stage == null)
			stage = new Stage();
		try {
		//load the fxml file
		loader = new FXMLLoader (getClass().getResource(filename));
		//add fxml into the scene
		scene = new Scene(loader.load());
		//set the scene to the stage		
		stage.setScene(scene);
		
		stage.setTitle(title);
		stage.show();
		} catch (Exception ex) {
			MsgDialog.displayDialog("Error caught", "Error launching the " + title);
			ex.printStackTrace();
		
		}
	}
	
	

}


