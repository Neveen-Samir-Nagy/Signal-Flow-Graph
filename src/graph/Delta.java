package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Delta {
	private LinkedList<LinkedList<Object[]>> nonLoops;
	private Gain g;
	LinkedList<Queue<Integer>> loops;

	public Delta() {
		nonLoops = new LinkedList<LinkedList<Object[]>>();
		loops = new LinkedList<Queue<Integer>>();
		g = new Gain();

	}

	public float calculateDelta(LinkedList<LinkedList<Object[]>> nonLoop, LinkedList<Queue<Integer>> loop) {
		float myDelta = 1;
		nonLoops = new LinkedList<LinkedList<Object[]>>();
		loops = new LinkedList<Queue<Integer>>();
		loops = loop;
		nonLoops = nonLoop;
		myDelta = (1) - sum();
		float tempDelta = 1;
		for (int i = 0; i < nonLoops.size(); i++) {
			tempDelta = 1;
			for (int j = 0; j < nonLoops.get(i).size(); j++) {
				Object[] temp = nonLoop.get(i).get(j);
				tempDelta = tempDelta * (g.calculateGain(temp));
			}
			if (nonLoops.get(i).size() % 2 == 0) {
				myDelta = myDelta + tempDelta;
			} else {
				myDelta = myDelta - tempDelta;
			}
		}
		return myDelta;

	}

	private float sum() {
		float sum = 0;
		for (int i = 0; i < loops.size(); i++) {
			Object[] temp = loops.get(i).toArray();
			sum = sum + g.calculateGain(temp);
		}
		return sum;

	}
	public void setgraph(float[][] matrix) {
		g.setGraph(matrix);
	}

}
