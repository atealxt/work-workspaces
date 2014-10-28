package io.github.atealxt.nlp;

public class CategorizedDocument extends Document {

	private Category category;
	private Object[][] vector;

	public CategorizedDocument(String name, String content) {
		super(name, content);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
		if (category != null) {
			category.addDocument(this);
		}
	}

	public Object[][] getVector() {
		return vector;
	}

	public void setVector(Object[][] vector) {
		this.vector = vector;
	}
}
