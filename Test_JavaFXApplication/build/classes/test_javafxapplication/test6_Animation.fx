/*
 * test6_Animation.fx
 *
 * Created on 2008/04/28, 14:14:31
 */

package test_javafxapplication;

import javafx.ui.*;  
import javafx.ui.canvas.*;  
import javafx.animation.*; 

/**
 * @author Administrator
 */

class MetronomeModel {  
  public attribute x2Val : Integer;  
  public attribute anim : Timeline;  
}  
  
Frame {  
  var metroModel =   
    MetronomeModel {}  
  title: "Animation Example"  
  width: 400  
  height: 500  
  visible: true  
  content:  
    BorderPanel {  
      center:  
        Canvas {  
          content:  
            Line {  
              x1: 200  
              y1: 400  
              x2: bind metroModel.x2Val  
              y2: 100  
              strokeWidth: 5  
              stroke: Color.RED  
            }  
        }  
      bottom:  
        FlowPanel {  
          content: [  
            Button {  
              text: "Start"  
              enabled: bind not metroModel.anim.running  
              action:  
                function():Void {  
                  metroModel.anim.start();  
                }  
            },  
            Button {  
              text: "Pause"  
              enabled: bind not metroModel.anim.paused and  
                                metroModel.anim.running   
              action:  
                function():Void {  
                  metroModel.anim.pause();  
                }  
            },  
            Button {  
              text: "Resume"  
              enabled: bind metroModel.anim.paused  
              action:  
                function():Void {  
                  metroModel.anim.resume();  
                }  
            },  
            Button {  
              text: "Stop"  
              enabled: bind metroModel.anim.running  
              action:  
                function():Void {  
                  metroModel.anim.stop();  
                }  
            }  
          ]  
        }  
    }  
}

class test6_Animation {

}

