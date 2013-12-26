package projecteuler;

public abstract class ProblemTemplate {

	public abstract String getTitle();

	public abstract String getURL();
	
	public abstract String getResult();

	@Override
	public String toString() {
		return "Problem: " + getTitle() + "\nURL: " + getURL();
	}
}
