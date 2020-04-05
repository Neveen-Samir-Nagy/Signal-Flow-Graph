package graph;

import java.util.LinkedList;
import java.util.Queue;

public class Gain {
	float myGain;
	private float[][] myGraph;
	
	public Gain() {
		myGain = 1;
		}

	public float calculateGain(Object[] q) {
		myGain =1;
		Object[] myQ = q;
		int node1 = (int) myQ[0];
		int node2;
		
		for (int i = 1; i < myQ.length; i++) {
			node2 = (int) myQ[i];
			myGain = myGain * myGraph[node1-1][node2-1];
			node1 = node2;
		}

		return myGain;

	}

	public void setGraph(float[][] matrix) {
		myGraph = new float[matrix.length][matrix.length];
		myGraph = matrix;
	}
}
