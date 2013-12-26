package projecteuler;

import java.io.File;

public class ProblemRunner {

	public static void main(String args[]) throws Exception {
		String[] problems;
		if (args.length == 1) {
			problems = new String[] { args[0] };
		} else {
			problems = new File("./src/projecteuler/problem").list();
			for (int i = 0; i < problems.length; i++) {
				problems[i] = problems[i].substring(0, problems[i].length() - 5);
			}
		}
		for (String problem : problems) {
			String className = "projecteuler.problem." + problem;
			Class<?> clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			System.out.println(obj);
			String result = (String) clazz.getMethod("getResult").invoke(obj);
			System.out.println("Result: " + result);
		}
	}
}
