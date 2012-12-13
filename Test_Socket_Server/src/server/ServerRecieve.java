package server;

import java.net.DatagramPacket;

/**
 * Server's receiving class
 * @author Atea
 */
public class ServerRecieve extends Thread{

    /**
     * Receiving datagram packets
     */
    private String PASSTEXT;
    
    @Override
    public void run(){
        recieve();
    }

    /**
     * Recieve from client
     */
    private void recieve(){
        try{
            while(true){

                //recieve from client
                byte dataRecieve[]= new byte[Short.MAX_VALUE];
                DatagramPacket dpRecieve = new DatagramPacket(dataRecieve,dataRecieve.length,Server.iaServer,1234);
                Server.dsServer.receive(dpRecieve);
                
                if(dpRecieve.getData() == null || dpRecieve.getData().equals("")){
                    Thread.sleep(300);
                    continue;
                }
                dataRecieve = dpRecieve.getData();
                
                //when client first run,post IP to server.
                String sTem = new String(dataRecieve).trim();
                if(!sTem.startsWith("up\n")){
                    this.PASSTEXT = sTem; 
                    System.out.println("Server recieved: " + this.PASSTEXT);
                    ServerSend ss = new ServerSend(this.PASSTEXT.getBytes());
                    ss.start();                    
                }
                else{
                    sTem = sTem.substring(3);
                    Server.addClientIp(getIPByte(sTem));
                }
            }
        }catch(Exception e){
                e.printStackTrace();
        }
    }
    
    /**
     * Get IP from string
     * @param IP String 
     * @return byte[]
     */
    private byte[] getIPByte(String IP){
        byte []bIP= new byte[4];
        int iTem;
        for(int iii=0;iii<4;iii++){
            if(IP.indexOf(".")!=-1){
                iTem= new Integer(IP.substring(0,IP.indexOf("."))).intValue();
            }
            else{
                iTem= new Integer(IP).intValue();
            }
            if(iTem>127){
                bIP[iii]=(byte)(iTem-256);
            }
            else{
                bIP[iii]=(byte)iTem;
            }
            if(IP.indexOf(".")!=-1){
                IP=IP.substring(IP.indexOf(".")+1,IP.length());
            }
        }
        return bIP;
    }    
    
}
