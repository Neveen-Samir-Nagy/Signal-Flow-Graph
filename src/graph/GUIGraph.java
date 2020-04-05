package graph;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class GUIGraph extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("Signal_Flow_Graph.fxml"));
		
		Scene scence = new Scene(root);
		primaryStage.getIcons().add(new Image("file:SFG.png"));
		primaryStage.setTitle("Signal Flow Graph");
		primaryStage.setScene(scence);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
