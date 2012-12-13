/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class Test_fileUpload extends ActionSupport implements ServletRequestAware {

    private File file;
    private String fileName;

    public String getMyDocFileName() {
        return fileName;
    }

    public void setMyDocFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getMyDoc() {
        return file;
    }

    public void setMyDoc(File file) {
        this.file = file;

        //file.renameTo(new File("D:/NetBeans 6.1/TEMP/a.html"));
    }
    private HttpServletRequest request;

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws Exception {

        System.out.println("this.fileName = " + this.fileName);
        System.out.println("this.file = " + this.file);

        //if used jquery,file is:
        System.out.println("this.myfileFileName = " + myfileFileName);
        System.out.println("this.myfileContentType = " + myfileContentType);
        System.out.println("this.file = " + myfile);

        //ps: jq plugins
        //http://valums.com/ajax-upload/
        //http://www.uploadify.com/demo/
        //http://www.malsup.com/jquery/form/#file-upload

        return SUCCESS;
    }
    private File myfile;
    private String myfileContentType;
    private String myfileFileName;

    public void setMyfile(File myfile) {
        this.myfile = myfile;
    }

    public void setMyfileContentType(String myfileContentType) {
        this.myfileContentType = myfileContentType;
    }

    public void setMyfileFileName(String myfileFileName) {
        this.myfileFileName = myfileFileName;
    }
}
