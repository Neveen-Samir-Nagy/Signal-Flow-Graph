package graph;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ControllerNodes implements Initializable {

	GUI g;
	@FXML
	TextField nodefrom = new TextField();
	@FXML
	TextField nodeto = new TextField();
	@FXML
	TextField getgain = new TextField();
	@FXML
	Button solve = new Button();
	@FXML
	Button clear = new Button();
	@FXML
	Button add = new Button();
	@FXML
	TextField clfrom = new TextField();
	@FXML
	TextField clto = new TextField();
	@FXML
	Label from = new Label();
	@FXML
	Label to = new Label();
	@FXML
	Label gain = new Label();
	@FXML
	Canvas can = new Canvas();
	@FXML
	Button back = new Button();
	LinkedList<Integer> grapgNodes;
	Object[] arrShapes;

	public void Draw(ActionEvent event) {
		arrShapes = new Object[3];
		GUI gg = g.getoneclass();
		GraphicsContext g = can.getGraphicsContext2D();
		int node1 = Integer.parseInt(nodefrom.getText());
		int node2 = Integer.parseInt(nodeto.getText());
		float gain = Float.valueOf(getgain.getText());
		if (node1 <= gg.sfg.numOFNodes() && node2 <= gg.sfg.numOFNodes() && node1 > 0 && node2 > 0 && gain != 0) {
			gg.sfg.addGain(node1, node2, gain);
			double radius = (grapgNodes.get(node1 - 1) + grapgNodes.get(node2 - 1)) / 2;
			double y = grapgNodes.get(node2 - 1) - grapgNodes.get(node1 - 1);
			arrShapes[0] = node1;
			arrShapes[1] = node2;
			arrShapes[2] = gg.sfg.getMyGraph()[node1 - 1][node2 - 1];
			gg.Shapes.add(arrShapes);
			g.setLineWidth(6);
			if (node2 - node1 == 1) {
				double x1 = grapgNodes.get(node1 - 1) + (y / 2);
				double[] x = new double[] { x1 + 30, x1 + 5, x1 + 5 };
				double y1 = 150 + 25;
				double[] y2 = new double[] { y1, y1 - 7, y1 + 7 };
				g.setStroke(Color.BLACK);
				g.strokeLine(grapgNodes.get(node1 - 1) + 50, 175, grapgNodes.get(node2 - 1), 175);
				g.setLineWidth(2);
				g.strokeText(String.valueOf(gg.sfg.getMyGraph()[node1 - 1][node2 - 1]), radius, 155);
				g.setFill(Color.BLACK);
				g.fillPolygon(x, y2, 3);
				g.setStroke(Color.GAINSBORO);
				g.setLineWidth(5);
				g.setStroke(Color.BLACK);
			} else if (node1 == node2) {
				double x1 = grapgNodes.get(node1 - 1) + (y / 2);
				double[] x = new double[] { x1 + 30, x1 + 10, x1 + 10 };
				double y1 = 98;
				double[] y2 = new double[] { y1, y1 - 5, y1 + 10 };
				g.setLineWidth(3);
				g.setStroke(Color.GOLD);
				g.strokeOval(grapgNodes.get(node1 - 1), 98, 50, 50);
				g.setLineWidth(2);
				g.strokeText(String.valueOf(gg.sfg.getMyGraph()[node1 - 1][node2 - 1]), radius + 10, 87);
				g.setFill(Color.GOLD);
				g.fillPolygon(x, y2, 3);
				g.setLineWidth(5);
			} else if (node2 > node1) {
				double x1 = grapgNodes.get(node1 - 1) + (y / 2);
				double[] x = new double[] { x1 + 30, x1 + 5, x1 + 5 };
				double y1 = 150 - (y / 6);
				double[] y2 = new double[] { y1, y1 - 7, y1 + 7 };
				g.setStroke(Color.GREEN);
				g.strokeArc(grapgNodes.get(node1 - 1) + 25, 150 - (y / 6), y, y / 3, 0, 180, ArcType.OPEN);
				g.setLineWidth(2);
				g.strokeText(String.valueOf(gg.sfg.getMyGraph()[node1 - 1][node2 - 1]), radius, (150 - (y / 6)) - 10);

				if ((150 - (y / 6)) - 10 <= can.getLayoutY()) {
					g.strokeText(String.valueOf(gg.sfg.getMyGraph()[node1 - 1][node2 - 1]), radius,
							can.getLayoutY() + 10);
				}
				g.setFill(Color.GREEN);
				g.fillPolygon(x, y2, 3);
				g.setStroke(Color.GREEN);
				g.setLineWidth(5);
			} else {
				double x1 = grapgNodes.get(node1 - 1) + (y / 2);
				double[] x = new double[] { x1 + 30, x1 + 30, x1 + 10 };
				double y1 = 150 + (-y / 6) + 50;
				double[] y2 = new double[] { y1 - 7, y1 + 7, y1 };
				g.setStroke(Color.YELLOWGREEN);
				g.strokeArc(grapgNodes.get(node2 - 1) + 25, 150 - (-y / 6) + 50, -y, -y / 3, 0, -180, ArcType.OPEN);
				g.setLineWidth(2);
				g.strokeText(String.valueOf(gg.sfg.getMyGraph()[node1 - 1][node2 - 1]), radius, 150 + (-y / 6) + 70);
				g.setFill(Color.YELLOWGREEN);
				g.fillPolygon(x, y2, 3);
				g.setStroke(Color.YELLOWGREEN);
				g.setLineWidth(5);
			}
		}
	}

	public void delet(ActionEvent event) {
		GUI gg = g.getoneclass();
		GraphicsContext g = can.getGraphicsContext2D();
		int node1 = Integer.parseInt(clfrom.getText());
		int node2 = Integer.parseInt(clto.getText());
		gg.sfg.deleteGain(node1, node2);
		for (int i = 0; i < gg.Shapes.size(); i++) {
			if ((node1 == (int) gg.Shapes.get(i)[0]) && (node2 == (int) gg.Shapes.get(i)[1])) {
				gg.Shapes.remove(i);
			}
		}
		g.clearRect(0, 0, 929.0, 800.0);
		int num = gg.sfg.numOFNodes();
		int move = ((int) can.getWidth()) / num;
		int hundred = 10;
		g.setLineWidth(5);
		for (int i = 0; i < num; i++) {
			g.setLineWidth(5);
			if (i == 0 || i == num - 1) {
				g.setStroke(Color.BLACK);
				g.setFill(Color.CHOCOLATE);
				g.strokeOval(hundred, 150, 50, 50);
				g.fillOval(hundred, 150, 50, 50);
			} else {
				g.setStroke(Color.BLACK);
				g.setFill(Color.GAINSBORO);
				g.strokeOval(hundred, 150, 50, 50);
				g.fillOval(hundred, 150, 50, 50);
			}
			g.setLineWidth(2);
			g.strokeText(String.valueOf(i + 1), hundred + 20, 180);
			hundred = move + hundred;
		}
		for(int i=0;i<gg.Shapes.size();i++) {
			drawshapes(i);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		GUI gg = g.getoneclass();
		GraphicsContext g = can.getGraphicsContext2D();
		int num = gg.sfg.numOFNodes();
		grapgNodes = new LinkedList<Integer>();
		int move = ((int) can.getWidth()) / num;
		int hundred = 10;
		g.setLineWidth(5);
		for (int i = 0; i < num; i++) {
			g.setLineWidth(5);
			if (i == 0 || i == num - 1) {
				g.setStroke(Color.BLACK);
				g.setFill(Color.CHOCOLATE);
				g.strokeOval(hundred, 150, 50, 50);
				g.fillOval(hundred, 150, 50, 50);
			} else {
				g.setStroke(Color.BLACK);
				g.setFill(Color.GAINSBORO);
				g.strokeOval(hundred, 150, 50, 50);
				g.fillOval(hundred, 150, 50, 50);
			}
			g.setLineWidth(2);
			g.strokeText(String.valueOf(i + 1), hundred + 20, 180);
			grapgNodes.add(hundred);
			hundred = move + hundred;
		}
		
		for(int j=0;j<gg.Shapes.size();j++) {
			drawshapes(j); 
			}
	}

	public void solve(ActionEvent event) throws IOException {
		Parent loader = FXMLLoader.load(getClass().getResource("Transfer_Function.fxml"));
		Scene scene = new Scene(loader);
		Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app.setScene(scene);
		app.show();
	}

	public void back(ActionEvent event) throws IOException {
		GUI gg = g.getoneclass();
		Parent loader = FXMLLoader.load(getClass().getResource("Signal_Flow_Graph.fxml"));
		Scene scene = new Scene(loader);
		Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app.setScene(scene);
		app.show();
	}

	private void drawshapes(int index) {
		GUI gg = g.getoneclass();
		int node1 = (int) gg.Shapes.get(index)[0];
		int node2 = (int) gg.Shapes.get(index)[1];
		float gain = (float) gg.Shapes.get(index)[2];
		GraphicsContext g = can.getGraphicsContext2D();
		double radius = (grapgNodes.get(node1 - 1) + grapgNodes.get(node2 - 1)) / 2;
		double y = grapgNodes.get(node2 - 1) - grapgNodes.get(node1 - 1);
		g.setLineWidth(6);
		if (node2 - node1 == 1) {
			double x1 = grapgNodes.get(node1 - 1) + (y / 2);
			double[] x = new double[] { x1 + 30, x1 + 5, x1 + 5 };
			double y1 = 150 + 25;
			double[] y2 = new double[] { y1, y1 - 7, y1 + 7 };
			g.setStroke(Color.BLACK);
			g.strokeLine(grapgNodes.get(node1 - 1) + 50, 175, grapgNodes.get(node2 - 1), 175);
			g.setLineWidth(2);
			g.strokeText(String.valueOf(gain), radius, 155);
			g.setFill(Color.BLACK);
			g.fillPolygon(x, y2, 3);
			g.setStroke(Color.GAINSBORO);
			g.setLineWidth(5);
			g.setStroke(Color.BLACK);
		} else if (node1 == node2) {
			double x1 = grapgNodes.get(node1 - 1) + (y / 2);
			double[] x = new double[] { x1 + 30, x1 + 10, x1 + 10 };
			double y1 = 98;
			double[] y2 = new double[] { y1, y1 - 5, y1 + 10 };
			g.setLineWidth(3);
			g.setStroke(Color.GOLD);
			g.strokeOval(grapgNodes.get(node1 - 1), 98, 50, 50);
			g.setLineWidth(2);
			g.strokeText(String.valueOf(gain), radius + 10, 87);
			g.setFill(Color.GOLD);
			g.fillPolygon(x, y2, 3);
			g.setLineWidth(5);
		} else if (node2 > node1) {
			double x1 = grapgNodes.get(node1 - 1) + (y / 2);
			double[] x = new double[] { x1 + 30, x1 + 5, x1 + 5 };
			double y1 = 150 - (y / 6);
			double[] y2 = new double[] { y1, y1 - 7, y1 + 7 };
			g.setStroke(Color.GREEN);
			g.strokeArc(grapgNodes.get(node1 - 1) + 25, 150 - (y / 6), y, y / 3, 0, 180, ArcType.OPEN);
			g.setLineWidth(2);
			g.strokeText(String.valueOf(gain), radius, (150 - (y / 6)) - 10);

			if ((150 - (y / 6)) - 10 <= can.getLayoutY()) {
				g.strokeText(String.valueOf(gain), radius, can.getLayoutY() + 10);
			}
			g.setFill(Color.GREEN);
			g.fillPolygon(x, y2, 3);
			g.setStroke(Color.GREEN);
			g.setLineWidth(5);
		} else {
			double x1 = grapgNodes.get(node1 - 1) + (y / 2);
			double[] x = new double[] { x1 + 30, x1 + 30, x1 + 10 };
			double y1 = 150 + (-y / 6) + 50;
			double[] y2 = new double[] { y1 - 7, y1 + 7, y1 };
			g.setStroke(Color.YELLOWGREEN);
			g.strokeArc(grapgNodes.get(node2 - 1) + 25, 150 - (-y / 6) + 50, -y, -y / 3, 0, -180, ArcType.OPEN);
			g.setLineWidth(2);
			g.strokeText(String.valueOf(gain), radius, 150 + (-y / 6) + 70);
			g.setFill(Color.YELLOWGREEN);
			g.fillPolygon(x, y2, 3);
			g.setStroke(Color.YELLOWGREEN);
			g.setLineWidth(5);
		}
	}

}
