package graph;
import java.util.LinkedList;
import java.util.Stack;

public class GetLoops {

	private float[][] Matrix;
	private int count = 0;
	private Stack<Integer> stack_of_loops;
	private LinkedList<Stack<Integer>> list_of_loops ;

	public GetLoops() {
		count = 0;
		stack_of_loops = new Stack<Integer>();
		list_of_loops = new LinkedList<Stack<Integer>>();
	}

	public LinkedList<Stack<Integer>> get_loops_for_TS(float[][] Matrix) {
		stack_of_loops = new Stack<Integer>();
		list_of_loops = new LinkedList<Stack<Integer>>();
		this.Matrix = new float[Matrix.length][Matrix.length];
		this.Matrix = Matrix;
		for (count = 0; count < Matrix.length; count++) {
			for (int column = 0; column < Matrix.length; column++) {
				if (Matrix[count][column] == 0) {
					continue;
				} else if (count == column && Matrix[count][column] != 0) {
					stack_of_loops.clear();
					stack_of_loops.push(count);
					stack_of_loops.push(count);
					Stack<Integer> temp = new Stack<Integer>();
					Object[] stack = new Object[stack_of_loops.size()];
					stack = stack_of_loops.toArray();
					for(int iter =0; iter<stack.length;iter++) {
						temp.add((Integer) stack[iter]+1);
					}
					list_of_loops.add(temp);
					stack_of_loops.pop();
				} else {
					if (!stack_of_loops.contains(count)) {
						stack_of_loops.push(count);
					}
					if (!stack_of_loops.contains(column)) {
						stack_of_loops.push(column);
						recursion_for_loops(column);
					}
				}
			}
			if (!stack_of_loops.empty()) {
				stack_of_loops.pop();
			}
		}
		RemoveAdditionalStacks remove = new RemoveAdditionalStacks();
		list_of_loops = remove.removeAddtional(list_of_loops);

		return list_of_loops;
	}

	private void recursion_for_loops(int j) {
		for (int i = 0; i < Matrix.length; i++) {
			if (Matrix[j][i] == 0) {
				continue;
			} else if (i == count) {
				stack_of_loops.push(count);
				Stack<Integer> temp = new Stack<Integer>();
				Object[] stack = new Object[stack_of_loops.size()];
				stack = stack_of_loops.toArray();
				for(int iter =0; iter<stack.length;iter++) {
					temp.add((Integer) stack[iter]+1);
				}
				list_of_loops.add(temp);
				stack_of_loops.pop();
			} else if (!stack_of_loops.contains(i)) {
				stack_of_loops.push(i);
				recursion_for_loops(i);
			}
		}
		if (!stack_of_loops.empty()) {
			stack_of_loops.pop();
		}
	}
}
