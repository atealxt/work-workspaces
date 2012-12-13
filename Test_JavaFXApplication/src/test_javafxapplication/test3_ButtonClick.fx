/*
 * test3_ButtonClick.fx
 *
 * Created on 2008/03/13, 11:17:40
 */

package test_javafxapplication;
import javafx.ui.*;
import java.lang.System;

/**
 * @author Administrator
 */

class test3_ButtonClick {

}

class ButtonClickModel {
    attribute numClicks: int;
}

var model = new ButtonClickModel();

var win = Frame {
    width: 220
    height: 200
    menubar: MenuBar {
         menus: Menu {
             text: "File"
             mnemonic: F
             items: MenuItem {
                 text: "Exit"
                 mnemonic: X
                 accelerator: {
                     modifier: ALT
                     keyStroke: F4
                 }
                 action: operation() {
                     System.exit(0);
                 }
             }
         }
    }    
    content: GridPanel {
        border: EmptyBorder {
           top: 30
           left: 30
           bottom: 30
           right: 30
        }
        rows: 2
        columns: 1
        vgap: 10
        cells:
        [Button {
             text: "I'm a button!"
             mnemonic: I
             action: operation() {
                 model.numClicks++;
            }
        },
        Label {
            text: bind "Number of button clicks: {model.numClicks}"
        }]
    }
    visible: true
};
