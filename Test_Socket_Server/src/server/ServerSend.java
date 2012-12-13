package server;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * Server's Sending class
 * @author Atea
 */
public class ServerSend extends Thread{
    
    /**
     * Sending datagram packets
     */    
    private byte dataSend[];

    /**
     * Construct server sending class to send datagram packets
     * @param b byte[]
     */
    public ServerSend(byte b[]){
        this.dataSend = b;
    }

    @Override
    public void run(){
        send();
    }

    /**
     * Send to clients
     */
    private void send(){
        //send to client
        DatagramPacket dpSend;
        for(InetAddress ia : Server.iaClientArr){
            try{
                dpSend = new DatagramPacket(dataSend,dataSend.length,ia,5678);
                Server.dsServer.send(dpSend);
                System.out.println("Server sent: " + new String(dataSend));   
            }
            catch(Exception e){
                Server.delClientIp(ia);
            }
        }        
    }
}






