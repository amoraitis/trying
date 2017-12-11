import java.util.*;

/**
 * 
 * @author amoraitis
 * Sliding Maximum simple solution
 * with O(nk)
 * TODO: Implement Optimal Solution
 */

public class SlidingMaximumApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("k: ");
		int k = scanner.nextInt();
		System.out.println("Size of array: ");
		int size = scanner.nextInt();
		Queue<Integer> array = new LinkedList<Integer>();
		for(int i=0; i<size; i++){
			int tmp = scanner.nextInt();
			array.add(tmp);
		}
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i=0; i<size; i++){
			if(i+k>size)break;
			for(int j=i; j<k; j++){
				temp.add((Integer) array.toArray()[j]);
			}
			int max = getMax(temp);
			System.out.println(max);
			array.poll();
		}

	}

	private static int getMax(ArrayList<Integer> temp) {
		int result = temp.get(0);
		for (int i=1; i<temp.size(); i++) {
			if(result<temp.get(i))result = temp.get(i);
		}
		return result;
	}

}
