package graph;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerTF implements Initializable {

	@FXML
	ListView paths = new ListView();

	@FXML
	ListView delta = new ListView();
	@FXML
	ListView loops = new ListView();

	@FXML
	ListView nonTouch = new ListView();

	@FXML
	Label tf = new Label();
	@FXML
	Label ldeltai = new Label();
	@FXML
	Label Lpath = new Label();
	@FXML
	Label Lloop = new Label();
	@FXML
	Label Ldelta = new Label();
	@FXML
	Label Lnontouch = new Label();
	@FXML
	Button back = new Button();
	@FXML
	Button New = new Button();

	@FXML
	TextField getTF = new TextField();
	@FXML
	TextField getDelta = new TextField();
	GUI g;

	public void NEW(ActionEvent event) throws IOException { 
		GUI gg = g.getoneclass();
		Parent loader = FXMLLoader.load(getClass().getResource("Signal_Flow_Graph.fxml"));
		Scene scene = new Scene(loader);
		Stage app = (Stage)((Node)event.getSource()).getScene().getWindow();
		app.setScene(scene);
		app.show();
	}
	
	public void back(ActionEvent event) throws IOException { 
		Parent loader = FXMLLoader.load(getClass().getResource("Nodes.fxml"));
		Scene scene = new Scene(loader);
		Stage app = (Stage)((Node)event.getSource()).getScene().getWindow();
		app.setScene(scene);
		app.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		GUI gg = g.getoneclass();
		LinkedList<Queue<Integer>> path = gg.sfg.getForwardPaths(gg.sfg.getMyGraph());
		ArrayList<Queue<Integer>> strpath = new ArrayList<Queue<Integer>>();
		for (int i = 0; i < path.size(); i++) {
			strpath.add(path.get(i));
			ObservableList<Queue<Integer>> items = FXCollections.observableArrayList(strpath);
			paths.setItems(items);
		}

		LinkedList<Queue<Integer>> loop = gg.sfg.getLoops(gg.sfg.getMyGraph());
		ArrayList<Queue<Integer>> strloop = new ArrayList<Queue<Integer>>();
		for (int i = 0; i < loop.size(); i++) {
			strloop.add(loop.get(i));
			ObservableList<Queue<Integer>> items = FXCollections.observableArrayList(strloop);
			loops.setItems(items);
		}
		ArrayList<Float> strpathloop = new ArrayList<Float>();
		for (int i = 0; i < path.size(); i++) {
			LinkedList<LinkedList<Object[]>> looppath = gg.sfg.getforwardpathloops(path.get(i), loop);
			strpathloop.add(gg.sfg.calculateDelta(looppath, gg.sfg.getloopsforeverypath()));
			ObservableList<Float> items = FXCollections.observableArrayList(strpathloop);
			delta.setItems(items);
		}
		LinkedList<LinkedList<Object[]>> non = gg.sfg.getNonTouchingLoops(loop);
		ArrayList<LinkedList<Queue>> strnon = new ArrayList<LinkedList<Queue>>();
		for (int i = 0; i < non.size(); i++) {
			LinkedList<Queue> q2 = new LinkedList<Queue>();
			for (int j = 0; j < non.get(i).size(); j++) {
				Object[] obj = non.get(i).get(j);
				Queue q = new LinkedList();
				for (int iter = 0; iter < non.get(i).get(j).length; iter++) {
					q.add(non.get(i).get(j)[iter]);
				}
				q2.add(q);
			}
			strnon.add(q2);
			ObservableList<LinkedList<Queue>> items = FXCollections.observableArrayList(strnon);
			nonTouch.setItems(items);
		}
		String delta = String
				.valueOf(gg.sfg.calculateDelta(gg.sfg.getNonTouchingLoops(gg.sfg.getLoops(gg.sfg.getMyGraph())),
						gg.sfg.getLoops(gg.sfg.getMyGraph())));
		getDelta.setText(delta);
		String tf = String.valueOf(gg.sfg.transferFunction());
		if(delta.equals("0.0")) {
			getTF.setText("Infinity");
		}else {
		getTF.setText(tf);
		}
	}
}
