package chatroom.client;

import java.net.DatagramPacket;

/**
 * Client's Sending class
 * @author Atea
 */
public class ClientSend extends Thread{

    /**
     * Sending datagram packets [ String ]
     */      
    private String PASSTEXT;
    
    /**
     * Sending datagram packets [ byte[] ]
     */      
    private byte dataSend[]; 
    
    /**
     * Construct client sending class to send datagram packets
     * @param str String
     */
    public ClientSend(String str){
        this.PASSTEXT = str;
        this.dataSend = PASSTEXT.getBytes();
    }

    @Override
    public void run(){
        send();
    }

    /**
     * Send to server
     */    
    private void send(){
        try{
            //send to server
            DatagramPacket dpSend= new DatagramPacket(dataSend,dataSend.length,Client.iaServer,1234);
            Client.dsClient.send(dpSend);
            System.out.println("Client sent: " + PASSTEXT );
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
