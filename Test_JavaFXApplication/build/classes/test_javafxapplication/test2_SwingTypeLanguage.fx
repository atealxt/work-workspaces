/*
 * test2_SwingTypeLanguage.fx
 *
 * Created on 2008/03/13, 11:03:36
 */

package test_javafxapplication;
import javafx.ui.*;

/**
 * @author Administrator
 */
class test2_SwingTypeLanguage {

    
}

var win = new Frame();
win.title = "Hello World JavaFX";
win.width = 200;
var label = new Label();
label.text = "Hello World";
win.content = label;
win.visible = true;