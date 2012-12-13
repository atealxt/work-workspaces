/*
 * Main.fx
 *
 * Created on 2008/03/07, 14:21:35
 */

package test_javafxapplication;
import javafx.ui.*;
import java.lang.System;
		
class classTest{
    attribute property1: String;
    operation opr1();
}
operation classTest.opr1(){
    var vTem='test!';
    System.out.println("JavaFX test: {vTem}");
    
}

var objTest = classTest{
    property1:"Property test"
};
var stockSymbol = TextField { };
stockSymbol.action = operation() {
    objTest.opr1();
};


Frame{
    title: 'Mashup Application'
    visible: true
    //width: 200 
    //height: 200

    /*
    content: 
        TextArea {
        font: Font { face: SERIF style: ITALIC size: 16 }
        lineWrap: true
        wrapStyleWord: true
        editable: false
        text: bind objTest.property1
        }
    */
    
    content: stockSymbol
};













        
