/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test_javase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class Test_StringFilter {
    
    void doFilter(){
        System.out.println("do filter");
        
        try{
            
            //Set<String> set = new TreeSet<String>();
            //Set<String> set = new LinkedHashSet<String>();
            List<String> list = new ArrayList<String>();
            
            String sTem=null;
            StringBuilder s = new StringBuilder();

            File f = new File("D:/WORK/課金cプログラムとシェルのメッセージ英語化/a.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f),"shift-jis" ) );
            
            int iii=0;
            //String ssss=null;
            sTem = in.readLine();
            while( sTem != null ){
                iii++;  
                
                /*
                if(iii==268){
                    System.out.println(sTem);
                    ssss = sTem;
                }
                if(iii==424){
                    System.out.println(sTem);
                    //ssss = sTem;
                    System.out.println(sTem.equals(ssss));
                }   
                 * */             
                
                //sTem=sTem.trim();     
                
                ///*
                //System.out.println(sTem);
                if(!list.contains(sTem)){
                    list.add(new String(sTem));
                    //list.add(sTem);
                }

                //*/
                //set.add(sTem);
                //set.add(new String(sTem));
                
                /*
                if(sTem.equals("構成テーブルに存在していません。"))
                    System.out.println(iii);
                 */
                
                sTem = in.readLine();
            }
            in.close();	 
            
            File ff = new File("D:/WORK/課金cプログラムとシェルのメッセージ英語化/b.txt");
            if(!ff.exists()){
                ff.createNewFile();
            }
            BufferedWriter br = new BufferedWriter(new FileWriter(ff));
            //Iterator i = set.iterator();
            Iterator i = list.iterator();
            while(i.hasNext()){
                //System.out.println(i.next());
                s.append((String)i.next()+"\n");
            }
            
            br.write(s.toString().toCharArray());
            br.close();	                        
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
