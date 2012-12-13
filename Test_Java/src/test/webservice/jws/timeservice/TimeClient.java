package test.webservice.jws.timeservice;

import static test.webservice.Constants.SERVICE_TIME;
import static test.webservice.Constants.SERVICE_URL;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * 此例子的Java客户端程序不依赖TimeServerImpl，只依赖TimeServer<br>
 * 还有一种声明服务的方法，可使客户端代码更少，但缺点是依赖于实现类。（此处略）
 */
class TimeClient {

    public static void main(final String args[]) throws Exception {
        URL url = new URL(SERVICE_URL + SERVICE_TIME + "?wsdl");

        // Qualified name of the service:
        // 1st arg is the service URI
        // 2nd is the service name published in the WSDL
        QName qname = new QName("http://timeservice.jws.webservice.test/", "TimeServerImplService");

        // Create, in effect, a factory for the service.
        Service service = Service.create(url, qname);

        // Extract the endpoint interface, the service "port".
        TimeServer server = service.getPort(TimeServer.class);

        System.out.println(server.getTimeAsString());
        System.out.println(server.getTimeAsElapsed());
        System.out.println(server.getMyBean());
        System.out.println(server.queryArr(3).length);
//        System.out.println(server.queryList(3).size());
        System.out.println(server.queryList(3).getBeans().size());
    }
}
