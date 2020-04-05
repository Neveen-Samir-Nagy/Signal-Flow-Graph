package graph;

import java.util.LinkedList;

public class GUI {

	SFG sfg;
	static LinkedList<Object[]> Shapes = null;
	private static GUI GG = null;
	private GUI() {
		sfg = new SFGIMP();
	}
	
	public static GUI getoneclass() {
		if(GG==(null)) {
			GG=new GUI();
			Shapes = new LinkedList<Object[]>();
		}
		return GG;
	}
}
