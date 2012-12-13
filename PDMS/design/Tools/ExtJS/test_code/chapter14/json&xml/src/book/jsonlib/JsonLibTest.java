package book.jsonlib;

import net.sf.json.JSONObject;

public class JsonLibTest {
	public static void main(String[] args){
		PhoneNumber homePhone = new PhoneNumber("լ��","123456");
		PhoneNumber officePhone = new PhoneNumber("�칫�绰","654321");
		Person person = new Person("tom",20,homePhone,officePhone);
		JSONObject json = JSONObject.fromObject(person);
		String jsonStr = json.toString();
		System.out.println(json);
	}
}
