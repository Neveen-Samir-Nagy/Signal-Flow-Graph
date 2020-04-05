package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Queue;

public class ForwardPathLoops {

	private LinkedList<Queue<Integer>> loopForwardpath;
	private LinkedList<Queue<Integer>> GetNonTouchingPath ;
	private LinkedList<LinkedList<Object []>> NonTouchingPath;
	private GetNonTouching non;
	public ForwardPathLoops() {
		loopForwardpath = new LinkedList<Queue<Integer>>();
		NonTouchingPath = new LinkedList<LinkedList<Object []>>();
		GetNonTouchingPath = new LinkedList<Queue<Integer>>();
		non= new GetNonTouching();
	}
	public void setloop(LinkedList<Queue<Integer>> list) {
		loopForwardpath = list;
	}
	public LinkedList<LinkedList<Object []>> getNonTouchingPath(Queue<Integer> path,LinkedList<Queue<Integer>> list){
		loopForwardpath = new LinkedList<Queue<Integer>>();
		NonTouchingPath = new LinkedList<LinkedList<Object []>>();
		GetNonTouchingPath = new LinkedList<Queue<Integer>>();
		non= new GetNonTouching();
		loopForwardpath = list;
		Object[] loop1 = path.toArray();
		Object[] loop2; 
		for(int i = 0; i<loopForwardpath.size();i++) {
			loop2 = loopForwardpath.get(i).toArray();
			if(non.compare(loop1, loop2)) {
				GetNonTouchingPath.add(loopForwardpath.get(i));
			}
		}

		NonTouchingPath = non.nonTouchLoop(GetNonTouchingPath);
		return NonTouchingPath;
		
	}
	public LinkedList<Queue<Integer>> getloopsforDelta(){
		return GetNonTouchingPath;
	}
	
}
