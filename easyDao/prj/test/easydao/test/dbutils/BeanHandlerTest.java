package easydao.test.dbutils;

import java.sql.SQLException;
import java.util.List;

import easydao.dbutils.DBUtils;
import easydao.test.TestBase;
import easydao.test.entity.Child;

public class BeanHandlerTest extends TestBase {

    @Override
    public void execute() throws Exception {
        single();
        list();
    }

    private void single() throws Exception {
        Child child = new DBUtils().querySingle(Child.class, "select * from child where name = ?", "jerry");
        System.out.println(child);
    }

    private void list() throws SQLException {
        List<Child> children = new DBUtils().queryList(Child.class, "select * from child");
        System.out.println(children);
    }
}
