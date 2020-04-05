package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class testinter {
	static Queue<Integer> q ;
	static float gain ;
	static SFGIMP sfg = new SFGIMP();
	public static void main(String[] args) {
		
		
		sfg.createGraph(4);
		/*
		 * sfg.addGain(1, 2, 1); sfg.addGain(1, 4, 3);
		 */
		sfg.addGain(2, 2, 2);
		/*
		 * sfg.addGain(3, 4, 1); sfg.addGain(4, 3, 4); sfg.addGain(2, 1, 5);
		 * sfg.addGain(3, 2, 1);
		 */
	
		float[][] graph = sfg.getMyGraph();
		LinkedList<Queue<Integer>> path = new LinkedList<Queue<Integer>>();
				path = sfg.getForwardPaths(graph);
		System.out.println("no. forward paths = "+ path.size());
		print(path);
		LinkedList<Queue<Integer>> loops = sfg.getLoops(graph);
		System.out.println("no. loops = "+ loops.size());
		print(loops);
		LinkedList<LinkedList<Object[]>> nonloops = sfg.getNonTouchingLoops(sfg.getLoops(graph));
		
		System.out.println("non touching loops : ");
		for(int i=0;i<nonloops.size();i++) {
			 LinkedList<Object[]> temp = nonloops.get(i);
			 
			 for(int j=0;j<temp.size();j++) {
				 
                 for(int k=0;k<temp.get(j).length;k++) {
               	  int tempo = (int) temp.get(j)[k];
               	  System.out.print(tempo+" ");
                 }
				 System.out.print(", ");
			 }
			 System.out.println();
		 }
		
		System.out.println(sfg.calculateDelta(nonloops, sfg.getLoops(graph)));
		 System.out.println(sfg.transferFunction());
		}
	
	public static void print(LinkedList<Queue<Integer>> path) {
		for(int i =0; i<path.size();i++) {
			   q = path.get(i);
				
				//gain = sfg.calculateGain(q.toArray());
				int size = q.size();
				
				for(int j=0; j<size;j++) {
				int temp = q.poll();
					if(j == size-1) {
					System.out.println(temp);
						//System.out.println("gain = " + gain);
					}
					else System.out.print(temp+" ");
				}
			}
		
			
	}

}
