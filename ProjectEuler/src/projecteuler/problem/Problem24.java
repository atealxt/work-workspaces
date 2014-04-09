package projecteuler.problem;

import java.util.Arrays;

import org.junit.Assert;

import projecteuler.ProblemTemplate;

public class Problem24 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Lexicographic permutations";
	}

	@Override
	public String getResult() {
		Assert.assertEquals("[1, 1, 0, 0]", getLexicographicPermutation(new int[] { 0, 0, 1, 1 }, 6));
		Assert.assertEquals("Not found!", getLexicographicPermutation(new int[] { 0, 0, 1, 1 }, 7));
		Assert.assertEquals("[2, 7, 8, 3, 9, 1, 5, 4, 6, 0]", getPermutation(1000000));
		return String.valueOf(getPermutation(1000000));
	}

	private String getPermutation(int n) {
		return getLexicographicPermutation(DIC, n);
	}

	/** Base on ASC ordered */
	private String getLexicographicPermutation(int[] arr, int n) {
		int[] array = Arrays.copyOf(arr, arr.length);
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

	public static int move(int[] array, int i) {
		if (array.length <= 1) {
			return -1;
		}
		while (array[i - 1] == array[i]) {
			i--;
			if (i == 0) {
				return -1;
			}
		}
		if (i == array.length - 1) {
			swap(array, i, i - 1);
			i--;
			if (i == 0) {
				return -1;
			} else {
				return i;
			}
		}
		while (array[i - 1] >= array[i]) {
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

	private static void swap(int[] array, int i, int j) {
		int x = array[i];
		array[i] = array[j];
		array[j] = x;
	}

	private final static int[] DIC = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
}
