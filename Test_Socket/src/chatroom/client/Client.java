/**
 * Connect to server,receiving and sending datagram packets
 */
package chatroom.client;

import chatroom.setting.Ip;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Client's main class
 * @author Atea
 */
public class Client {
    
    /**
     * Server IP
     */    
    public static InetAddress iaServer;
    
    /**
     * Client IP
     */        
    public static InetAddress iaClient;
    
    /**
     * Client datagram packets
     */    
    public static DatagramSocket dsClient;
    
    /**
     * Get IP and connect to server
     */
    public static void test(){
        try{
            Ip.getIP();
            iaServer=InetAddress.getByAddress(Ip.getIPByte(Ip.serverIP));
            iaClient=InetAddress.getByName(InetAddress.getLocalHost().getHostName()); 
            dsClient = new DatagramSocket(5678,iaClient);

            ClientSend cr = new ClientSend("up\n" + iaClient.getHostAddress());
            cr.start();
            cr.join();
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }            
    }
    
    /**
     * Get username
     */
    public static String getUserName(){
        return Ip.userName;
    }
}
