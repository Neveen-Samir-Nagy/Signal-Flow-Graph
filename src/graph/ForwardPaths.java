package graph;
import java.util.LinkedList;
import java.util.Queue;


public class ForwardPaths {
	private float[][] myGraph;
	private LinkedList<Queue<Integer>> paths;
	private Queue<Integer> path;

	public ForwardPaths() {
		paths = new LinkedList<>();
		path = new LinkedList<>();
	}

	public LinkedList<Queue<Integer>> calculateForwadPaths(float[][] myGraph) {
		paths = new LinkedList<>();
		path = new LinkedList<>();
		this.myGraph = myGraph;
     	FindPath(0);
        return paths;
	}

	private void FindPath(int index) {
		if (index == myGraph.length - 1) {
			path.add(index+1);

			Queue<Integer> temp = new LinkedList<>();
			Queue<Integer> tempQ = new LinkedList<>();
			int size = path.size();
			for (int k = 0; k < size; k++) {
				temp.add(path.poll());
			}
			for (int k = 0; k < size; k++) {
				tempQ.add(temp.peek());
				path.add(temp.poll());
			}
			paths.add(tempQ);

			path.remove(index+1);
			path.remove(index );
			return;
		}
		int i = index;
		int j;
		for (j = i + 1; j < this.myGraph.length; j++) {
			if (myGraph[i][j] != 0) {
				path.add(i+1);
				FindPath(j);
				path.remove(i+1);
			}
		}
		return;
	}
}
