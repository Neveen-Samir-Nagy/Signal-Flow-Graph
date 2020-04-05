package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public interface SFG {
	
public void createGraph(int nodes);

public int numOFNodes();

public void addGain(int fromNode, int toNode, float gain);

public void deleteGain(int fromNode, int toNode);
public LinkedList<LinkedList<Object []>> getforwardpathloops(Queue<Integer> path,LinkedList<Queue<Integer>> list);
public LinkedList<Queue<Integer>> getloopsforeverypath();
public float[][] getMyGraph();
public double transferFunction();

public LinkedList<Queue<Integer>> getForwardPaths(float[][] graph);

public LinkedList<Queue<Integer>> getLoops(float[][] graph);

public LinkedList<LinkedList<Object[]>> getNonTouchingLoops(LinkedList<Queue<Integer>> loop);

public float calculateDelta(LinkedList<LinkedList<Object[]>> nonLoop, LinkedList<Queue<Integer>> loop);

public  float calculateGain(Object[] q);
}
