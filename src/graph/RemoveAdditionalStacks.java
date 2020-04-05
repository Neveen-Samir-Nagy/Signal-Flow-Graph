package graph;

import java.util.LinkedList;
import java.util.Stack;

public class RemoveAdditionalStacks {

	private LinkedList<Stack<Integer>> stacks_of_loops ;
	private LinkedList<LinkedList<Object[]>> link_of_link;
	private int flag = 0;
	public RemoveAdditionalStacks() {
		stacks_of_loops = new LinkedList<Stack<Integer>>();
		link_of_link = new LinkedList<LinkedList<Object[]>>();
	}
	
	public LinkedList<Stack<Integer>> removeAddtional(LinkedList<Stack<Integer>> stacks_of_loop){
		stacks_of_loops = new LinkedList<Stack<Integer>>();
		link_of_link = new LinkedList<LinkedList<Object[]>>();
		this.stacks_of_loops = stacks_of_loop;
		for(int i=0;i<stacks_of_loops.size();i++) {
			flag = 0;
			for(int j=i+1;j<stacks_of_loops.size();j++) {
				flag = 0;
				if(stacks_of_loops.get(i).size()!=stacks_of_loops.get(j).size()) {
					continue;
				}else {
				for(int iter=0;iter<stacks_of_loops.get(i).size();iter++) {
					if(stacks_of_loops.get(j).contains(stacks_of_loops.get(i).get(iter))) {
						flag ++;
					}
				}
				if(flag==stacks_of_loops.get(i).size()&&flag==stacks_of_loops.get(j).size()) {
					stacks_of_loops.remove(j);
					j--;
				}
			}
			}
		}
		return stacks_of_loops;
	}
	
	public LinkedList<LinkedList<Object[]>> removeLinkOfLink(LinkedList<LinkedList<Object[]>> non){
		link_of_link = non;
		int flag2=0;
		for(int i=0;i<link_of_link.size();i++) {
			flag = 0;
			flag2 = 0;
			for(int j=i+1;j<link_of_link.size();j++) {
				flag2=0;
				if(link_of_link.get(i).size()!=link_of_link.get(j).size()) {
					continue;
				}else {
				for(int iter=0;iter<link_of_link.get(i).size();iter++) {
					Object[] temp = link_of_link.get(i).get(iter);
					for(int iter2=0;iter2<link_of_link.get(j).size();iter2++) {
					flag = 0;
					Object[] temp2 = link_of_link.get(j).get(iter2);
					for(int index=0;index<temp.length;index++) {
						if(index>=temp2.length) {
							break;
						}
						if(temp[index]==temp2[index]) {
						flag ++;
						}
					}
					if(flag == temp.length && flag == temp2.length) {
						flag2++;
					}
					}
				}
				if(flag2==link_of_link.get(i).size()&&flag2==link_of_link.get(j).size()) {
					link_of_link.remove(j);
					j--;
				}
			}
			}
		}
		return link_of_link;
		
	}
}
