package chatroom.setting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * SAX XML formater
 * @since 5.0
 */
class MyContentHandler implements ContentHandler {
    private StringBuffer buf;
    public void setDocumentLocator( Locator locator ) {}
    public void startDocument() throws SAXException {
        buf=new StringBuffer();
    }
    public void endDocument() throws SAXException {}
    public void processingInstruction( String target, String instruction ) throws SAXException {}
    public void startPrefixMapping( String prefix, String uri ) {}
    public void endPrefixMapping( String prefix ) {}
    public void startElement( String namespaceURI, String localName,String fullName, Attributes attributes ) throws SAXException {
        //System.out.println("\nElement: " + "["+fullName+"]" +"Start");
        for ( int i = 0; i < attributes.getLength(); i++ ) {
            //System.out.println("\tAttributes:" + attributes.getLocalName(i) + "Value:" + attributes.getValue(i));
        }
    }
    public void endElement( String namespaceURI, String localName, String fullName ) throws SAXException {
	String nullStr="";
        if (!buf.toString().trim().equals(nullStr)){
           	//System.out.println("\tContext: " + buf.toString().trim());
        	if(fullName.trim().equals("server")){
           		Ip.serverIP=buf.toString().trim();
           	}
                else if(fullName.trim().equals("name")){
           		Ip.userName=buf.toString().trim();
           	}                
        }
        buf.setLength(0);
        //System.out.println("Element: "+"["+fullName+"]"+" End");
    }
    public void characters( char[] chars, int start, int length )throws SAXException {
          buf.append(chars,start,length);
    }
    public void ignorableWhitespace( char[] chars, int start, int length ) throws SAXException {}
    public void skippedEntity( String name ) throws SAXException {}
}

/**
 * Get the Server IP and the username from XML
 * @author Atea
 */
public class Ip{

        /**
         * Server IP
         */
	public static String serverIP="";
        
        /**
         * User name
         */        
        public static String userName="";

        /**
         * Get the Server IP and the username. 
         * @exception  Exception 
         */
	public static void getIP() throws Exception{
            File f = null;
            try{
                f=new File("setting/setting.xml" );
            }catch(Exception e){
                System.exit(-1);
            }
            
            StringBuilder s = new StringBuilder();
            String sTem = new String();
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f) ) );
            sTem = in.readLine();
            while( sTem != null ){
                s.append(sTem +"\n");
                sTem = in.readLine();
            }
            sTem = s.toString().replaceAll("&amp;","&").replaceAll("&","&amp;");
            BufferedWriter br = new BufferedWriter(new FileWriter(f));
	    br.write(sTem.toCharArray());
	    br.close();
            
            XMLReader reader = XMLReaderFactory.createXMLReader() ;
            ContentHandler contentHandler = new MyContentHandler();
            reader.setContentHandler( contentHandler );
            try{
                reader.parse("setting/setting.xml"); 
            }catch(Exception e){
                try{
                    reader.parse("src/setting/setting.xml"); 
                }catch(Exception ex){
                    ex.printStackTrace();
                    return;
                }  
            }   

            System.out.println("serverIP: " + serverIP);
	}
        
        /**
         * Set server IP
         * @param str String Server IP
         */
        public static void setServerip(String str){
            serverIP = str;
        }        

        /**
         * Get IP from string
         * @param IP String 
         * @return byte[]
         */        
	public static byte[] getIPByte(String IP){
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