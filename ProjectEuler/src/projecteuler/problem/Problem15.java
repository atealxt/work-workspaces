package projecteuler.problem;

import java.math.BigInteger;
import java.util.LinkedList;

import projecteuler.ProblemTemplate;

public class Problem15 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Lattice paths";
	}

	@Override
	public String getResult() {
		return String.valueOf(getNumOfRoutes2(20));
	}

	private String getNumOfRoutes2(int width) {
		return factorial(2 * width).divide(factorial(width).pow(2)).toString();
	}

	public static BigInteger factorial(int n) {
		BigInteger x = new BigInteger("1");
		for (int i = 2; i <= n; i++) {
			x = x.multiply(new BigInteger(String.valueOf(i)));
		}
		return x;
	}

	@SuppressWarnings("unused")
	private int getNumOfRoutes(int width) {
		int numOfRoutes = 1;
		LinkedList<Point> fork = new LinkedList<>();
		Point start = new Point(0, width);
		fork.add(start);
		Point p;
		while (!fork.isEmpty()) {
			p = fork.pop();
			if (p.x == width && p.y == 0) {
				break;
			}
			if (p.x == width) {
				fork.add(new Point(p.x, p.y - 1));
			} else if (p.y == 0) {
				fork.add(new Point(p.x + 1, p.y));
			} else {
				numOfRoutes++;
				fork.add(new Point(p.x, p.y - 1));
				fork.add(new Point(p.x + 1, p.y));
			}
		}
		return numOfRoutes;
	}

	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
}

// Reference: http://www.robertdickau.com/manhattan.html
