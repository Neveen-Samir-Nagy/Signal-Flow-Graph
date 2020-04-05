package graph;

import java.io.IOException;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ControllerGuiGraph {

	@FXML
	TextField signal = new TextField();

	@FXML
	Label enter = new Label();

	@FXML
	TextField nodes = new TextField();
	
	@FXML
	Button contin = new Button();
	GUI gg;
	
	public void setNumOfNodes(ActionEvent event) throws IOException {
		GUI g = gg.getoneclass();
		int num = Integer.parseInt(nodes.getText());
		g.sfg.createGraph(num);
		gg.Shapes = new LinkedList<Object[]>();
		Parent loader = FXMLLoader.load(getClass().getResource("Nodes.fxml"));
		Scene scene = new Scene(loader);
		Stage app = (Stage)((Node)event.getSource()).getScene().getWindow();
		app.setScene(scene);
		app.show();
	}
	public void continuebefore(ActionEvent event) throws IOException {
		GUI g = gg.getoneclass();
		if(g.Shapes.size()!=0) {
			Parent loader = FXMLLoader.load(getClass().getResource("Nodes.fxml"));
			Scene scene = new Scene(loader);
			Stage app = (Stage)((Node)event.getSource()).getScene().getWindow();
			app.setScene(scene);
			app.show();
		}else {
			gg.Shapes = new LinkedList<Object[]>();
		}
	}
}
