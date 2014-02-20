package projecteuler.problem;

import java.util.Arrays;

import projecteuler.ProblemTemplate;

public class Problem24 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Lexicographic permutations";
	}

	@Override
	public String getResult() {
		return String.valueOf(getLexicographicPermutation(1000000));
	}

	/** Base on ASC ordered with no duplicated item */
	private String getLexicographicPermutation(int n) {
		int[] array = Arrays.copyOf(DIC, DIC.length);
		int i = array.length - 1;
		int cnt = 0;
		do {
			if (++cnt == n) {
				return Arrays.toString(array);
			}
			i = move(array, i);
		} while (i != -1);
		if (cnt == n) {
			return Arrays.toString(array);
		}
		return "Not found!";
	}

	private int move(int[] array, int i) {
		if (i == array.length - 1) {
			swap(array, i, i - 1);
			return i - 1;
		}
		while (array[i - 1] > array[i]) {
			i--;
			if (i == 0) {
				return -1;
			}
		}
		for (int j = array.length - 1; j > i; j--) {
			if (array[j] > array[i - 1]) {
				swap(array, j, i - 1);
				Arrays.sort(array, i, array.length);
				return array.length - 1;
			}
		}
		swap(array, i, i - 1);
		Arrays.sort(array, i, array.length);
		return array.length - 1;
	}

	private void swap(int[] array, int i, int j) {
		int x = array[i];
		array[i] = array[j];
		array[j] = x;
	}

	private final static int[] DIC = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
}
