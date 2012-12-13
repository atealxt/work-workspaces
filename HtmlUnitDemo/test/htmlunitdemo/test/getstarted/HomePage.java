package htmlunitdemo.test.getstarted;


import htmlunitdemo.test.TestBase;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HomePage extends TestBase {

    @Override
    public void excute() throws Exception {

        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage("http://atealxt.appspot.com/guestbook");
        Assert.assertEquals("Guestbook - Hero's Grave", page.getTitleText());

        String pageAsXml = page.asXml();// 和实际的网页源码不完全一致，经过了格式化等处理。小心使用！
        Assert.assertTrue(pageAsXml.contains("<script src=\"/javascripts/guestbook.js\" type=\"text/javascript\">"));
        logger.info(pageAsXml);

        String pageAsText = page.asText();
        Assert.assertTrue(pageAsText
                .contains("Welcome to my personal website, hope you to find out your own territory!"));
        logger.info(pageAsText);

        webClient.closeAllWindows();
    }

}
