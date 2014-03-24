package projecteuler.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import projecteuler.ProblemTemplate;

public class Problem30 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Digit fifth powers";
	}

	@Override
	public String getResult() {
		Assert.assertTrue(validNumber(1634, Arrays.asList(1, 6, 3, 4)));
		Assert.assertTrue(validNumber(1634, Arrays.asList(6, 1, 3, 4)));
		Assert.assertFalse(validNumber(1634, Arrays.asList(9, 1, 3, 4)));
		Assert.assertTrue(validNumber(8208, Arrays.asList(8, 2, 0, 8)));
		Assert.assertFalse(validNumber(4434, Arrays.asList(1, 3, 4, 8)));
		Assert.assertEquals(19316, getSumOfNum2(4));
		Assert.assertEquals(443839, getSumOfNum2(5));
		return String.valueOf(getSumOfNum2(5));
	}

	private int getSumOfNum2(int pow) {
		cache.clear();
		int total = 0;
		int begin = 10;
		int end = (int) (Math.pow(10, pow + 1) - 1);
		while (begin++ <= end) {
			int[] numbers = split(begin);
			String cacheKey = Arrays.toString(numbers);
			if (existCache(cacheKey)) {
				continue;
			}
			int sumOfPowers = 0;
			for (Integer i : numbers) {
				sumOfPowers += Math.pow(i, pow);
			}
			if (sumOfPowers == 1) {
				continue;
			}
			if (hasEq(numbers, sumOfPowers)) {
				total += sumOfPowers;
			}
			saveToCache(cacheKey);
		}
		return total;
	}

	private boolean hasEq(int[] numbers, int sumOfPowers) {
		int i = numbers.length - 1;
		while (i != -1) {
			int number = parseNumber(numbers);
			if (number == sumOfPowers) {
				if (numbers[0] == 0) {
					return false;
				} else {
					return true;
				}
			}
			i = Problem24.move(numbers, i);
		}
		return false;
	}

	private int parseNumber(int[] numbers) {
		StringBuilder sb = new StringBuilder();
		for (int i : numbers) {
			sb.append(i);
		}
		return Integer.parseInt(sb.toString());
	}

	private int[] split(int begin) {
		List<Integer> list = new ArrayList<>();
		int x = begin;
		while (x != 0) {
			list.add(x % 10);
			x = x / 10;
		}
		int[] arr = new int[list.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
		Arrays.sort(arr);
		return arr;
	}

	private final Map<String, Boolean> cache = new HashMap<>();

	private void saveToCache(String key) {
		cache.put(key, true);
	}

	private boolean existCache(String key) {
		return cache.containsKey(key);
	}

	/** Incorrect implementation with only considered combination (no repeat) case. Keep it in code base for combination algorithm. */
	@SuppressWarnings("unused")
	private int getSumOfNum(int pow) {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] code = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int total = 0;
		while (next(code)) {
			List<Integer> combination = getCombination(array, code);
			if (combination.size() == 1) {
				continue;
			}
			int sumOfPowers = 0;
			for (Integer i : combination) {
				sumOfPowers += Math.pow(i, pow);
			}
			if (validNumber(sumOfPowers, combination)) {
				total += sumOfPowers;
			}
		}
		throw new UnsupportedOperationException("Incorrect implementation");
	}

	private List<Integer> getCombination(int[] array, int[] code) {
		List<Integer> combination = new ArrayList<>(array.length);
		for (int i = 0; i < code.length; i++) {
			if (code[i] != 0) {
				combination.add(array[i]);
			}
		}
		return combination;
	}

	private boolean next(int[] code) {
		for (int i = code.length - 1; i >= 0; i--) {
			if (code[i] != 0) {
				continue;
			}
			for (int j = i - 1; j >= 0; j--) {
				if (code[j] == 1) {
					code[j] = 0;
					code[j + 1] = 1;
					move1ToHead(code, j + 2);
					return true;
				}
			}
			code[0] = 1;
			move1ToHead(code, 1);
			return true;
		}
		return false;
	}

	private void move1ToHead(int[] code, int range) {
		for (int i = code.length - 1; i > range; i--) {
			if (code[i] == 0) {
				continue;
			}
			for (int j = range; j < i; j++) {
				if (code[j] == 0) {
					code[j] = 1;
					code[i] = 0;
					break;
				}
			}
		}
	}

	private boolean validNumber(int n, List<Integer> list) {
		int _n = n;
		List<Integer> _list = new ArrayList<>(list);
		int c = 0;
		int size = _list.size();
		while (_n > 0) {
			int y = _n - _n / 10 * 10;
			_n = _n / 10;
			c++;
			if (!_list.remove(Integer.valueOf(y))) {
				return false;
			}
		}
		return c == size;
	}
}
