package chatroom.client;

import java.net.DatagramPacket;

/**
 * Client's receiving class
 * @author Atea
 */
public class ClientRecieve extends Thread{

    /**
     * Receiving datagram packets
     */    
    private String PASSTEXT;
    
    /**
     * Receive or not
     * true: receive
     * false: not receive
     */
    private boolean DO = true;  
    
    /**
     * Get datagram packets
     * @return String
     */
    public String getRecieve(){
        return this.PASSTEXT;
    }
    
    /**
     * When receive complate,clear datagram packets and wait next time
     */
    public void getComplete(){
        this.PASSTEXT = null;
    }    
    
    /**
     * Set receive or not
     * @param b boolean 
     */
    public void setDo(boolean b){
        this.DO = b;
    }    
    
    @Override
    public void run(){
        recieve();
    }

    /**
     * Recieve from server
     */    
    private void recieve(){
        try{
            while(DO){
                
                if(this.PASSTEXT!=null){
                    Thread.sleep(100);
                    continue;
                }
                
                //recieve from server
                byte dataRecieve[]= new byte[Short.MAX_VALUE];
                DatagramPacket dpRecieve = new DatagramPacket(dataRecieve,dataRecieve.length,Client.iaClient,5678);
                Client.dsClient.receive(dpRecieve);
                
                this.PASSTEXT = new String(dpRecieve.getData()).trim();
                System.out.println("Client recieved: " + this.PASSTEXT);
            }
        }catch(Exception e){}
    }
    
    /**
     * Disconnect to server 
     */
    public static void close(){
        try{
            Client.dsClient.close();
        }
        catch(Exception e){}
    }    
}