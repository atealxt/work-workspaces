package test.webservice.jws.timeservice; // time server

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import test.MyBean;

@WebService
@SOAPBinding(style = Style.RPC)
// more on this later
public interface TimeServer {

    @WebMethod
    String getTimeAsString();

    @WebMethod
    long getTimeAsElapsed();

    @WebMethod
    MyBean getMyBean();

    @WebMethod
    MyBean[] queryArr(int size);

//    @WebMethod
//    List<String> queryList(int size);

    MyBeanList queryList(int size);
}
