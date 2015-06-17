package test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class DistributeApple {

	public static void main(String args[]) {
		String[] person = { "A", "B", "C" };
		int[] weight = { 5, 3, 2 }; // even if weight is double not int, we can
									// calculate it from least common multiple.
									// If double decimal is too long, we can
									// still estimate a percentage at most as we
									// can.
		int numOfApples = 10;
		calc(person, weight, numOfApples);
	}

	private static void calc(String[] person, int[] weight, int n) {

		List<String> weightedPerson = new ArrayList<>();

		for (int i = 0; i < weight.length; i++) {
			int w = weight[i];
			for (int j = 0; j < w; j++) {
				weightedPerson.add(person[i]);
			}
		}

		Map<String, Integer> result = new TreeMap<>();
		for (int i = 0; i < person.length; i++) {
			result.put(person[i], 1); // make sure everyone have at least one apple.
		}

		for (int i = 0; i < n - person.length; i++) {
			int x = new Random().nextInt(weightedPerson.size());
			String s = weightedPerson.get(x);
			result.put(s, result.get(s) + 1);
		}

		System.out.println(result);
	}
}
