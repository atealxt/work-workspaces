/*
 * Main.fx
 *
 * Created on 2008/03/18, 16:50:48
 */

package test_socket;
import javafx.ui.*;
import java.lang.*;
import java.util.*;
import java.text.*;
import javax.swing.ImageIcon;
import chatroom.client.*;

class ClientFX{
    attribute objClient:Client;    
    attribute PASSTEXT: String;  
    attribute RECEIVE: String;
    attribute MSG: Vector;
    attribute userName: String;
    operation init(obj:ClientFX,tr:ClientRecieve,control:TextArea);  
    operation setShowMSG(obj:ClientFX);
    operation setPASSTEXT(obj:ClientFX,str:String);
    operation showMSGConsole(obj:Vector);
    operation send(obj:ClientFX,control:TextArea);
    operation clearMSG(obj:ClientFX,control:TextArea);
}

//obj
var objClientFX = ClientFX;     //ClientFX
var controlMSG = new TextArea();    //MSG
var controlPASSTEXT = new TextField(); //PASSTEXT
var tr = new ClientRecieve(); //receive

//method
operation ClientFX.init(obj:ClientFX,tr:ClientRecieve,control:TextArea){
    obj.objClient = new Client();
    obj.MSG = new Vector();
    obj.objClient.test();
    obj.userName = obj.objClient.getUserName();
  
    tr.start();    
    do{          
        while (true){                        
            if(tr.getRecieve() <> null){
                obj.RECEIVE = tr.getRecieve(); 
                tr.getComplete();
                obj.setShowMSG(obj);

                control.text="";
                var i=0;
                var length=0;
                var str =new StringBuffer();
                for(i in obj.MSG.toArray()){
                    length++;
                    if(length>50){
                        str = new StringBuffer(str.substring(str.indexOf("\n")+1));                    
                    }
                    str.append(i);str.append("\n");                        
                }   
                control.text=str.toString();
            }            
            Thread.sleep(100);
        }      
    }
}
operation ClientFX.setPASSTEXT(obj:ClientFX,str:String) {
    obj.PASSTEXT = str;
}
operation ClientFX.setShowMSG(obj:ClientFX) { 
    var dateFormat = new StringBuffer();
    var date = new Date();
    var hours = date.hours;
    var minutes = date.minutes;
    var seconds = date.seconds;
    if(hours<10){
        dateFormat.append("0");
    }
    dateFormat.append(hours);
    dateFormat.append(":");
    if(minutes<10){
        dateFormat.append("0");
    }    
    dateFormat.append(minutes);
    dateFormat.append(":");
    if(seconds<10){
        dateFormat.append("0");
    }       
    dateFormat.append(seconds);
    dateFormat.append(" ");
    dateFormat.append(obj.userName);
    dateFormat.append(" said: ");
    
    dateFormat.append(obj.RECEIVE);
    obj.MSG.add(dateFormat);
}
operation ClientFX.showMSGConsole(obj:Vector){
    var i=0;
    for(i in obj.toArray()){
        System.out.println(i);
    }
}
operation ClientFX.send(obj:ClientFX,control:TextArea){
    var ts = new ClientSend(obj.PASSTEXT);
    ts.start();
}
operation ClientFX.clearMSG(obj:ClientFX,control:TextArea){
    obj.MSG.clear();
    control.text = "";
}


//Frame
var mainFrame = new Frame();    //Frame
mainFrame.title = "css chatroom";
mainFrame.width=500;
mainFrame.height=545;
mainFrame.centerOnScreen=true;
mainFrame.resizable=false;
/*var icoPath = "/img/a.ico";
var iconShow = Image;
var ii = new ImageIcon(icoPath);
var ico = Icon;
ico.icon = ii;
iconShow.image = ico.getImage();
iconShow.url= icoPath;
mainFrame.iconImage = iconShow;*/
mainFrame.onOpen = operation(){ 
    objClientFX.init(objClientFX,tr,controlMSG);
};
mainFrame.onClose = operation(){
    try{
        while(true){
            if(tr.isAlive()){ 
                tr.close();
                tr.join();
                break;
            }
        }
        tr = null;
        System.gc();
        System.exit(0);
    }catch(e:Exception){}
};

//menu
var mb = new MenuBar();
var mu = new Menu();
mu.text="File";
mu.mnemonic=F;
var mi=new MenuItem();
mi.text ="Exit";
mi.mnemonic=X;
mi.accelerator={
    modifier: ALT
    keyStroke: F4
};
mi.action = operation() {
    System.exit(0);
};
//mi.icon = iconShow;

var miClear=new MenuItem();
miClear.text ="Clear MSG";
miClear.accelerator={
    modifier: CTRL
    keyStroke: D
};
miClear.action = operation(){    
    objClientFX.MSG.clear();
    controlMSG.text="";
};
mu.items = [miClear,mi];
mb.menus = mu;
mainFrame.menubar = mb;

//content
var borderpanel= new BorderPanel();
var emptyborder = new EmptyBorder();
emptyborder.left =5;
emptyborder.right =5;
emptyborder.top =5;
emptyborder.bottom =5;
borderpanel.border = emptyborder;

//top
var gridpanel = new GridPanel();
var compoundborder = new CompoundBorder();
compoundborder.borders= [
                            TitledBorder { title: "Context"}, 
                            EmptyBorder {
                                top: 5
                                left: 5
                                bottom: 5
                                right: 5
                            }
];
gridpanel.border=compoundborder;
gridpanel.rows =1;
gridpanel.columns =1;
controlMSG.rows=21;
controlMSG.editable = false;
controlMSG.focusTraversalKeysEnabled = false;
gridpanel.cells = controlMSG;
borderpanel.top =gridpanel;

//bottom
var gridbagpanel = new GridBagPanel();
var compoundborder2 = new CompoundBorder();
compoundborder2.borders= [
                            TitledBorder {title: "Talk"},
                            EmptyBorder {
                                top: 0
                                left: 5
                                bottom: 5
                                right: 5
                            }
];
gridbagpanel.border=compoundborder2;
var gridcell = new GridCell();
gridcell.anchor = EAST;
gridcell.gridx= 0;
gridcell.gridy= 0;
var simplelabel = new SimpleLabel();
simplelabel.text= "Input msg: ";
gridcell.content = simplelabel;
var gridcell2 = new GridCell();
gridcell2.anchor= WEST;
gridcell2.fill= HORIZONTAL;
gridcell2.weightx= 1;
gridcell2.gridx= 1;
gridcell2.gridy= 0;
controlPASSTEXT.onKeyTyped= operation(e:KeyEvent){
    if(e.keyChar == "\n" and controlPASSTEXT.value.trim() <> ""){
        objClientFX.PASSTEXT = controlPASSTEXT.value;
        controlPASSTEXT.value = "";
        objClientFX.send(objClientFX,controlMSG);
    }
};
gridcell2.content = controlPASSTEXT;
gridbagpanel.cells = [gridcell,gridcell2];
borderpanel.bottom =gridbagpanel;
mainFrame.content = borderpanel;
mainFrame.visible = true;






