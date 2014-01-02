package projecteuler.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import projecteuler.ProblemTemplate;

public class Problem5 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Smallest multiple";
	}

	@Override
	public String getURL() {
		return "http://projecteuler.net/problem=5";
	}

	@Override
	public String getResult() {
		return String.valueOf(getSmallestMultipleWay2(20));
	}

	private int getSmallestMultipleWay2(int maxNum) {
		List<Integer> multiple = new ArrayList<>();
		for (int i = 2; i <= maxNum; i++) {
			int n = i;
			for (Integer m : multiple) {
				if (n % m == 0) {
					n = n / m;
				}
				if (n <= 1) {
					break;
				}
			}
			if (n > 1) {
				multiple.add(n);
			}
		}
		int total = 1;
		for (int i : multiple) {
			total *= i;
		}
		return total;
	}

	@SuppressWarnings("unused")
	@Deprecated
	private int getSmallestMultipleWay1(int maxNum) {
		List<Integer> sequence = getEvenlyDivisibleSequence(maxNum);
		int n = sequence.get(0) + 1;
		boolean next = false;
		do {
			for (int i : sequence) {
				if (n % i != 0) {
					next = true;
					break;
				}
			}
			if (next) {
				n++;
				next = false;
				continue;
			} else {
				return n;
			}
		} while (true);
	}

	private List<Integer> getEvenlyDivisibleSequence(int maxNum) {
		Stack<Integer> toCheck = new Stack<>();
		List<Integer> sequence = new ArrayList<>();
		for (int i = 2; i <= maxNum; i++) {
			toCheck.add(i);
		}
		while (!toCheck.isEmpty()) {
			Integer num = toCheck.pop();
			for (int i = 0; i < toCheck.size(); i++) {
				Integer n = toCheck.get(i);
				if (num % n == 0) {
					toCheck.remove(n);
					i--;
				}
			}
			sequence.add(num);
		}
		return sequence;
	}
}
