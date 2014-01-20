package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem14 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Longest Collatz sequence";
	}

	@Override
	public String getResult() {
		return String.valueOf(getStartingNum(1000000));
	}

	private long getStartingNum(int maxNum) {
		int max = 0;
		int m = 0;
		int[] cache = new int[maxNum]; // index: number; value: length of chain.
		for (int i = 1; i < maxNum; i++) {
			long n = i;
			int c = 0;
			while (n != 1) {
				c++;
				n = getNext(n);
				if (n < maxNum && cache[(int) n] != 0) {
					c += cache[(int) n];
					break;
				}
			}
			if (max < c) {
				max = c;
				m = i;
			}
			cache[i] = c;
		}
		return m;
	}

	private long getNext(long n) {
		if (n % 2 == 0) {
			return n / 2;
		} else {
			return 3 * n + 1;
		}
	}
}
