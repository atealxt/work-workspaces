/*
 * test1_DropEvent.fx
 *
 * Created on 2008/03/10, 16:06:53
 */

package test_javafxapplication;

import javafx.ui.*;
import javafx.ui.canvas.*;
import javafx.ui.filter.*;
import java.io.File;
import java.lang.System;
import java.awt.datatransfer.DataFlavor;
import java.awt.image.BufferedImage;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.ByteArrayOutputStream;

/**
 * @author Administrator
 */

class test1_DropEvent {
    
}

operation isInputStreamReader(e:CanvasDropEvent):Boolean {
    var yes=false;
    try{
        (InputStreamReader)(e.transferData[0]);
        yes=true;
    } catch(any) {
        //ignore
    }
    return yes;
}
operation isFiles(e:CanvasDropEvent):Boolean {
    var yes=false;
    try{
        (File)(e.transferData[0]);

        System.out.print("e:");
        System.out.println(e);
    
        yes=true;
    } catch(any) {
        //ignore
    }
    return yes;
}
operation isBufferedImage(e:CanvasDropEvent):Boolean {
    var yes=false;
    try{
        (BufferedImage)(e.transferData[0]);
        yes=true;
    } catch(any) {
        //ignore
    }
    return yes;
}
operation isURL(e:CanvasDropEvent):Boolean {
    var yes=false;
    try{
        (URL)(e.transferData[0]);
        yes=true;
    } catch(any) {
        //ignore
    }
    return yes;
}

Frame {
    var: me
    visible: true
    width: 300
    height: 300
    title: "Drop something here"
    content: Canvas {
        canAcceptDrop: operation(e:CanvasDropEvent):Boolean {
            return true;
        }
        onDrop: operation(e:CanvasDropEvent) {
            var info;
            if(isFiles(e)){
                var file=(File)(e.transferData[0]);
                var fileName=file.getAbsolutePath();
                info="this is File\n{fileName}";
            } else {
                if(isBufferedImage(e)){
                    var image=(BufferedImage)(e.transferData[0]);
                    var h=image.getHeight();
                    var w=image.getWidth();
                    info="this is BufferedImage\nwidth={w}, height={h}";
                } else {
                    if(isURL(e)){
                        var url=(URL)(e.transferData[0]);
                        info="this is URL\n{url}";
                    }else {
                        if(isInputStreamReader(e)){
                            var isr=(InputStreamReader)(e.transferData[0]);
                            var baos=new ByteArrayOutputStream();
                            var b=isr.read();
                            while(b>-1){
                                baos.write(b);
                                b=isr.read();
                            }
                            info="this is InputStreamReader\n{baos}";
                        } else {
                            var o=e.transferData[0];
                            info="unknown\n{o}";
                        }
                    }
                }
            }
            MessageDialog {
                owner: me
                visible: true
                message: info
            };
        }
    }
}