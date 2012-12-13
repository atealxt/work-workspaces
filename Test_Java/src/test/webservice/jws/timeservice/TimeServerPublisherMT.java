package test.webservice.jws.timeservice;

import static test.webservice.Constants.SERVICE_TIME;
import static test.webservice.Constants.SERVICE_URL;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.ws.Endpoint;

class TimeServerPublisherMT { // MT for multithreaded

    private Endpoint endpoint;

    public static void main(final String[] args) {
        TimeServerPublisherMT self = new TimeServerPublisherMT();
        self.create_endpoint();
        self.configure_endpoint();
        self.publish();
    }

    private void create_endpoint() {
        endpoint = Endpoint.create(new TimeServerImpl());
    }

    private void configure_endpoint() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        endpoint.setExecutor(executor);
    }

    private void publish() {
        endpoint.publish(SERVICE_URL + SERVICE_TIME);
        System.out.println("Published TimeServer.");
    }
}
