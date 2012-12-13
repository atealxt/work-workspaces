package register;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class MySSHAccountRegister {

    private static String FF_DIR;

    public static void main(final String args[]) throws Exception {
        System.out.println("Sign up start");
        if (args.length > 0) {
            System.out.println("Firefox dir is: " + args[0]);
            FF_DIR = args[0];
        }
        new MySSHAccountRegister().register("http://www.sshcenter.info/signup.php");
        System.out.println("Sign up end");
    }

    public void register(final String url) throws Exception {
        final WebClient webClient = new WebClient();
        submit(webClient, url);
        webClient.closeAllWindows();
    }

    private void submit(final WebClient client, final String url) throws Exception {
        final HtmlPage page1 = client.getPage(url);
        final HtmlForm form = (HtmlForm) page1.getElementByName("formUser");

        final String s = "atea" + new SimpleDateFormat("yyyyMMdd").format(new Date());

        final HtmlTextInput username = form.getInputByName("username");
        username.focus();
        username.setValueAttribute(s);
        username.blur();

        final HtmlTextInput email = form.getInputByName("email");
        email.focus();
        email.setValueAttribute(s + "@" + s + ".com");
        email.blur();

        final HtmlPasswordInput password = form.getInputByName("password");
        password.focus();
        password.setValueAttribute(s);
        password.blur();

        final HtmlPasswordInput confirm_password = form.getInputByName("confirm_password");
        confirm_password.focus();
        confirm_password.setValueAttribute(s);
        confirm_password.blur();

        final HtmlTextInput verifycode = form.getInputByName("verifycode");
        final String captcha = getCaptcha(page1);
        verifycode.focus();
        verifycode.setValueAttribute(captcha);
        verifycode.blur();

        Thread.sleep(2 * 1000);
        final HtmlSubmitInput button = form.getInputByName("Submit");
        final HtmlPage page2 = button.click();
        System.out.println("Register result: " + page2.asXml());
        System.out.println("Account created: " + s);
    }

    /** 现在没有得到很好的验证码识别程序，只能手动识别了T_T */
    private String getCaptcha(final HtmlPage page1) throws IOException {
        final HtmlImage img = (HtmlImage) page1.getElementsByTagName("img").get(0);
        final ImageReader imageReader = img.getImageReader();
        final BufferedImage bufferedImage = imageReader.read(0);

        String temdir = System.getProperty("java.io.tmpdir");
        final String osName = System.getProperty("os.name");
        System.out.println("OS: " + osName);
        String console = null;
        if (osName.toLowerCase().contains("windows")) {
            console = "cmd";
        } else {
            temdir += "/";
            console = "bash";
        }

        final String captcha = temdir + "captcha.jpg";
        final OutputStream output = new FileOutputStream(captcha);
        ImageIO.write(bufferedImage, "jpg", output);
        final ProcessBuilder builder = new ProcessBuilder(console, "/c", "firefox", captcha);
        if (FF_DIR != null) {
            builder.directory(new File(FF_DIR));
        }
        final Process process = builder.start();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String decode = reader.readLine();
        System.out.println("captcha: " + decode);
        reader.close();
        process.destroy();
        return decode;
    }
}
