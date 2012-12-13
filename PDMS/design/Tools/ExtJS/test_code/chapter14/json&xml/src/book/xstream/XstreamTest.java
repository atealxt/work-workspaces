package book.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XstreamTest {

	public static void main(String[] args){
		PhoneNumber homePhone = new PhoneNumber("լ��","123456");
		PhoneNumber officePhone = new PhoneNumber("�칫�绰","654321");
		Person person = new Person("tom",20,homePhone,officePhone);
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("person",Person.class);
		String xml = xStream.toXML(person);
		System.out.println(xml);
	}
}
