package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SFGIMP implements SFG {
	private float[][] myGraph;
    private ForwardPaths forwardPath;
    private GetLoops getLoops ;
    private Gain gain ;
    private ForwardPathLoops fpl;
    private Delta delta ;
    private int numOFnodes=0;
    private ForwardPathLoops forwardLoops ;
    private GetNonTouching nonTouching ;
    
    public SFGIMP() {
    	forwardPath = new ForwardPaths();
    	getLoops = new GetLoops();
    	gain = new Gain();
    	delta = new Delta();
    	forwardLoops = new ForwardPathLoops();
    	nonTouching = new GetNonTouching();
    	fpl = new ForwardPathLoops();

	}
	@Override
	public void createGraph(int nodes) {
		numOFnodes = nodes;
		myGraph = new float[nodes][nodes];
		for (int i = 0; i < nodes; i++) {
			for(int j=0;j< nodes ;j++) {
				myGraph[i][j] = 0;
			}
			
		}

	}

	@Override
	public void addGain(int fromNode, int toNode, float gain) {
		myGraph[fromNode-1][toNode-1] = gain;

	}
	

	@Override
	public float[][] getMyGraph() {
		return myGraph;
	}

	@Override
	public LinkedList<Queue<Integer>> getForwardPaths(float[][] graph) {
		LinkedList<Queue<Integer>> paths = new LinkedList<Queue<Integer>>();
			paths =	forwardPath.calculateForwadPaths(graph);
		
		return paths;
	}

	@Override
	public LinkedList<Queue<Integer>> getLoops(float[][] graph) {
		LinkedList<Stack<Integer>> stackLoops = getLoops.get_loops_for_TS(myGraph);
		LinkedList<Queue<Integer>> Qloops = new LinkedList<>();
		
		for(int i =0 ; i<stackLoops.size();i++) {
			Queue<Integer> q = new LinkedList<>();
			for(int j=0;j<stackLoops.get(i).size();j++) {
				q.add(stackLoops.get(i).get(j));
				
			}
			Qloops.add(q);
		}
		return Qloops;
	}

	@Override
	public LinkedList<LinkedList<Object[]>> getNonTouchingLoops(LinkedList<Queue<Integer>> loop) {
		LinkedList<LinkedList<Object[]>> nonTouchingL = nonTouching.nonTouchLoop(loop);
		return nonTouchingL;
	}

	@Override
	public float calculateDelta(LinkedList<LinkedList<Object[]>> nonLoop, LinkedList<Queue<Integer>> loop) {
		delta.setgraph(myGraph);
		float d = delta.calculateDelta(nonLoop, loop);
		return d;
	}

	
	@Override
	public float calculateGain(Object[] q) {
		gain.setGraph(myGraph);
		float g = gain.calculateGain(q);
		return g;
	}
	@Override
	public int numOFNodes() {
		// TODO Auto-generated method stub
		return numOFnodes;
	}
	@Override
	public void deleteGain(int fromNode, int toNode) {
		// TODO Auto-generated method stub
		myGraph[fromNode-1][toNode-1] = 0;
	}
	@Override
	public LinkedList<LinkedList<Object[]>> getforwardpathloops(Queue<Integer> path,LinkedList<Queue<Integer>> list) {
		// TODO Auto-generated method stub
		return fpl.getNonTouchingPath(path,list);
	}
	@Override
	public LinkedList<Queue<Integer>> getloopsforeverypath() {
		// TODO Auto-generated method stub
		return fpl.getloopsforDelta();
	}
	@Override
	public double transferFunction() {
		// TODO Auto-generated method stub
		double TF = 0;
	    LinkedList<Queue<Integer>> paths = getForwardPaths(myGraph);
	    LinkedList<Queue<Integer>> loops = getLoops(myGraph);
		float delta = calculateDelta(getNonTouchingLoops(loops), loops);
		for(int i=0;i<paths.size();i++) {
			LinkedList<LinkedList<Object[]>> loopPath = getforwardpathloops(paths.get(i), loops);
			LinkedList<Queue<Integer>> loopPath2 = fpl.getloopsforDelta();
			TF = TF + (calculateGain(paths.get(i).toArray())*calculateDelta(loopPath, loopPath2));
		}
		return TF/delta;
	}

}
