package projecteuler;

import org.junit.Test;

public abstract class ProblemTemplate {

	public abstract String getTitle();

	public abstract String getURL();

	public abstract String getResult();

	@Override
	public String toString() {
		return "Problem: " + getTitle();
	}

	@Test
	public void execute() {
		System.out.println(this);
		System.out.println("Result: " + getResult());
	}
}
