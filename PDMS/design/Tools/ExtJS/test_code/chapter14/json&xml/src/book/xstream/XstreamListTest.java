package book.xstream;

import java.util.ArrayList;
import java.util.List;

import book.util.ExtHelper;

public class XstreamListTest {
	public static void main(String[] args){
		PhoneNumber homePhone = new PhoneNumber("լ��","123456");
		PhoneNumber officePhone = new PhoneNumber("�칫�绰","654321");
		List phoneList = new ArrayList();
		phoneList.add(homePhone);
		phoneList.add(officePhone);;
		String xml = ExtHelper.getXmlFromList(phoneList.size(), phoneList);
		System.out.println(xml);
	}
}
