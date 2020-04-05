package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class testGraph {

	public static void main(String[] args) {
		LinkedList<Queue<Integer>> paths = new LinkedList();
		Queue<Integer> q = new LinkedList<>(); 
		ForwardPaths f = new ForwardPaths();
		Gain g = new Gain();
		int[][] graph =  {
				{2,1,5,0},
				{0,0,0,6},
				{0,3,0,8},
			     {0,0,0,0},
		};
//		paths = f.calculateForwadPaths(graph);
//		g.setGraph(graph);
//		System.out.println(paths.size());
//		float gain =0;
//		
//		for(int i =0;i<paths.size();i++) {
//			q = paths.get(i);
//			
//			gain = g.calculateGain(q);
//			int size = q.size();
//			
//			for(int j=0; j<size;j++) {
//				int temp = q.poll()+1;
//				if(j == size-1) {
//					System.out.println(temp);
//					System.out.println(gain);
//				}
//				else System.out.print(temp+" ");
//			}
//			
//		}
		
		LinkedList<Queue<Integer>> loops = new LinkedList<>();
		q = new LinkedList<>();
		 q.add(1); q.add(2); q.add(1);
		 loops.add(q);
		 q = new LinkedList<>();
		 q.add(3); q.add(4); q.add(3);
		 loops.add(q);
		 q = new LinkedList<>();
		 q.add(5); q.add(6); q.add(5);
		 loops.add(q);
		 q = new LinkedList<>();
		 q.add(7); q.add(8); q.add(7);
		 loops.add(q);
		 q = new LinkedList<>();
		 q.add(1); q.add(2); q.add(3); q.add(1);
		 loops.add(q);
		 
		 GetNonTouching get = new GetNonTouching();
		 LinkedList<LinkedList<Object[]>> non = get.nonTouchLoop(loops);
		 System.out.println(non.size());
		 for(int i=0;i<non.size();i++) {
			 LinkedList<Object[]> temp = non.get(i);
			 for(int j=0;j<temp.size();j++) {

                  for(int k=0;k<temp.get(j).length;k++) {
                	  int tempo = (int) temp.get(j)[k];
                	  System.out.print(tempo+" ");
                  }
				 System.out.print(", ");
			 }
			 System.out.println();
		 }
		 
	}
	

}
