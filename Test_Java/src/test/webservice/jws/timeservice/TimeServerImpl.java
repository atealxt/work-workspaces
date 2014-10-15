package test.webservice.jws.timeservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import test.MyBean;

@WebService(endpointInterface = "test.webservice.jws.timeservice.TimeServer")
public class TimeServerImpl implements TimeServer {

    public String getTimeAsString() {
        return new Date().toString();
    }

    public long getTimeAsElapsed() {
        return new Date().getTime();
    }

    @Override
    public MyBean getMyBean() {
        MyBean o = new MyBean();
        o.setCcc(3);
        o.setDdd("ddd");
        return o;
    }

    @Override
    public MyBean[] queryArr(final int size) {
        MyBean[] arr = new MyBean[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new MyBean();
        }
        return arr;
    }

//    @Override
//    public List<String> queryList(final int size) {
//        List<String> list = new ArrayList<String>(size);
//        for (int i = 1; i <= size; i++) {
//            list.add(String.valueOf(i));
//        }
//        return list;
//    }

    @Override
    public MyBeanList queryList(final int size) {
        MyBeanList list = new MyBeanList();
        List<MyBean> beans = new ArrayList<MyBean>();
        for (int i = 1; i <= size; i++) {
            beans.add(new MyBean());
        }
        list.setBeans(beans);
        return list;
    }
}
