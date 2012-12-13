package server;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Vector;

/**
 * Server's main class
 * @author Atea
 */
public class Server{

    /**
     * Server IP
     */
    public static InetAddress iaServer;
    
    /**
     * Temporary Client IP
     */
    private  static InetAddress iaClient;
    
    /**
     * Clients IP
     */    
    public static Vector<InetAddress> iaClientArr;
    
    /**
     * Server datagram packets
     */
    public static DatagramSocket dsServer;

    /**
     * Add client IP
     * @param b byte[] 
     */
    public static void addClientIp(byte b[]){
        try{
            iaClient=InetAddress.getByAddress(b);
            if(!iaClientArr.contains(iaClient)){
                iaClientArr.add(iaClient);
                System.out.println("Added client ip: " + iaClient.getHostAddress());
            }    
            iaClient = null;
        }
        catch(Exception e){
            e.printStackTrace();
        }           
    }
    
    /**
     * Delete client IP
     * @param ia InetAddress 
     */    
    public static void delClientIp(InetAddress ia){
        try{
            iaClientArr.remove(ia);
            System.out.println("Deleted client ip: " + ia.getHostAddress());
        } 
        catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    public static void main(String args[]){
        try{ 
            iaServer=InetAddress.getByName(InetAddress.getLocalHost().getHostName()); 
            dsServer = new DatagramSocket(1234,iaServer);  
            iaClientArr = new Vector<InetAddress>();

            ServerRecieve tr = new ServerRecieve();
            tr.start();
            
            System.out.println("Server started.");
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }            
    }
}

