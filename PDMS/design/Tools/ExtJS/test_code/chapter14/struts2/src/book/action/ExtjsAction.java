package book.action;

import java.util.ArrayList;
import java.util.List;

import book.bean.Person;

import com.opensymphony.xwork2.ActionSupport;

public class ExtjsAction extends ActionSupport {
	private long results;
	private List items;

	public long getResults() {
		return results;
	}

	public void setResults(long results) {
		this.results = results;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public String execute() throws Exception{
		this.results = 3;

		Person p1 = new Person("����",29,"��","1979-09-13");
		Person p2 = new Person("����",28,"��","1980-10-11");
		Person p3 = new Person("����",27,"��","1981-01-23");
		this.items = new ArrayList();
		this.items.add(p1);
		this.items.add(p2);
		this.items.add(p3);

		return SUCCESS;
	}
}
