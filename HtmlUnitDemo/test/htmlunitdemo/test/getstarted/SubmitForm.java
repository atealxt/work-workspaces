package htmlunitdemo.test.getstarted;

import htmlunitdemo.test.TestBase;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.ConfirmHandler;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class SubmitForm extends TestBase {

    @Override
    public void excute() throws Exception {

        submitCancel();
        submitOk();
    }

    private void submitCancel() throws Exception {
        WebClient webClient = new WebClient();
        addHandler(webClient, false);
        submit(webClient);
        webClient.closeAllWindows();
    }

    /** <b>注意，WebClient不手动加Handler的话，默认是提交表单</b> */
    private void submitOk() throws Exception {
        WebClient webClient = new WebClient();
        addHandler(webClient, true);
        submit(webClient);
        webClient.closeAllWindows();
    }

    private void submit(final WebClient webClient) throws Exception {

        HtmlPage page1 = webClient.getPage("http://localhost:8090/HtmlUnitDemo/");
        HtmlForm form = page1.getFormByName("myform");

        HtmlTextInput textField = form.getInputByName("text1");
        textField.setValueAttribute("hello htmlunit");

        HtmlSubmitInput button = form.getInputByName("button1");
        HtmlPage page2 = button.click();// 若提交了表单，则返回提交后表单对象，否则返回当前表单对象
        Assert.assertNotNull(page2);
        logger.info(page2);
    }

    private void addHandler(final WebClient webClient, final boolean submitYesOrNo) {

        webClient.setConfirmHandler(new ConfirmHandler() {

            @Override
            public boolean handleConfirm(final Page page, final String message) {
                logger.info(message + " - " + submitYesOrNo);
                return submitYesOrNo;
            }
        });

    }

}
