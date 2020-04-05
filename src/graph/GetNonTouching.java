package graph;

import java.util.LinkedList;
import java.util.Queue;

public class GetNonTouching {
	private LinkedList<LinkedList<Object[]>> nonTouching;
	private LinkedList<Queue<Integer>> loops;

	public GetNonTouching() {
		nonTouching = new LinkedList<>();
		loops = new LinkedList<>();
	}

	public LinkedList<LinkedList<Object[]>> nonTouchLoop(LinkedList<Queue<Integer>> loop) {
		nonTouching = new LinkedList<>();
		loops = new LinkedList<>();
		this.loops = loop;
        int max = loops.size();
		int count = 2;
       while (count <= max) {
			rec(count);
			count++;
		}
		return nonTouching;

	}

	public boolean compare(Object[] loop1, Object[] loop2) {
		for (int i = 0; i < loop1.length; i++) {
			for (int j = 0; j < loop2.length; j++) {
				int l1 = (int) loop1[i];
				int l2 = (int) loop2[j];

				if (l1 == l2) {
					return false;
				}
			}
		}
		return true;

	}

	private void rec(int count) {

		Object[] loop1;
		Object[] loop2;

		for (int i = 0; i < loops.size(); i++) {
			int remove = 1;
			loop1 = loops.get(i).toArray();

			LinkedList<Object[]> temp = new LinkedList<>();
			temp.add(loop1);
			for (int j = i + 1; j < loops.size(); j++) {
				loop2 = loops.get(j).toArray();
				if (compare(loop1, loop2)) {
					boolean flag = false;
					for (int l = 1; l < temp.size(); l++) {
						if (!compare(loop2, temp.get(l))) {
							flag = true;
						}
					}
					if (!flag) {
						temp.add(loop2);
					}

					if (temp.size() == count) {
						LinkedList<Object[]> tempNon = new LinkedList<>();
						for (int k = 0; k < temp.size(); k++) {
							tempNon.add(temp.get(k));
						}
						nonTouching.add(tempNon);
						temp.remove(temp.size() - 1);

					}
					

				}
				
				if (j == loops.size() - 1) {
					if (remove < temp.size()) {
						temp.remove(remove);
						remove++;
						j = i + 1;
					}
				}
			}
			
			
		}
	}


}
