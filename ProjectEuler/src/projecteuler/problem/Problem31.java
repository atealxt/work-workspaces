package projecteuler.problem;

import java.util.ArrayList;
import java.util.List;

import projecteuler.ProblemTemplate;

public class Problem31 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Coin sums";
	}

	@Override
	public String getResult() {
		return String.valueOf(getNumOfWays(200));
	}

	private int getNumOfWays(int sumPence) {
		int sumWays = 0;
		while (Problem30.next(CODE)) {
			List<Integer> combination = Problem30.getCombination(ARRAY, CODE);
			int ways = getWays(sumPence, combination);
			sumWays += ways;
		}
		return sumWays;
	}

	private int getWays(int leftPences, List<Integer> combination) {
		if (leftPences == 0) {
			if (combination.isEmpty()) {
				return 1;
			} else {
				return 0;
			}
		}
		if (combination.isEmpty()) {
			return 0;
		}
		int ways = 0;
		List<Integer> list = new ArrayList<>(combination);
		int coin = list.remove(list.size() - 1);
		for (int i = 1; i <= leftPences / coin; i++) {
			ways += getWays(leftPences - coin * i, list);
		}
		return ways;
	}

	private static int[] ARRAY = { 1, 2, 5, 10, 20, 50, 100, 200 };
	private static int[] CODE = { 0, 0, 0, 0, 0, 0, 0, 0 };
}
