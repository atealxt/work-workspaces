package org.ywq.test;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ywq.common.BaseDAOHibernate;
import org.ywq.common.PageModel;
import org.ywq.entity.User;
import org.ywq.service.IUserService;

/**
 * @author ai5qiangshao E-mail:ai5qiangshao@163.com
 * @version ����ʱ�䣺Aug 5, 2009 10:27:01 PM
 * @Package org.ywq.test
 * @Description ��˵��
 */
@SuppressWarnings("unchecked")
public class Test extends TestCase {
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	BaseDAOHibernate<User> dao = (BaseDAOHibernate<User>) context
			.getBean("dao");

	public void testUserServiceSave() {
		IUserService service = (IUserService) context.getBean("userService");
		for (int i = 0; i < 150; i++) {
			service.save(new User(null, "ǿ��", 22, "��"));
		}
		PageModel<User> us = service.list(2, 5,"ss");
		System.out.println(us.getTotal() + "�ܼ�¼��");
		System.out.println(us.getTotalPage() + "��ҳ��");
		List<User> usl = us.getDatas();
		System.out.println(usl.get(0).getUname() + "=========");
	}

	public void testDao() {
		// List<User> uslist = dao
		// .find(new HibernateQueryBuilder<User>(User.class));
		// System.out.println(uslist.get(0).getUname());
	}

	public void testCreateTable() {
		// Configuration config = new Configuration().configure();

		// org.hibernate.tool.hbm2ddl.SchemaExport export = new SchemaExport(
		// config);
		// export.create(true, true);

	}

}
