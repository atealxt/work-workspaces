package htmlunitdemo.test.getstarted;

import htmlunitdemo.test.TestBase;

import java.util.List;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FindElement extends TestBase {

    @Override
    public void excute() throws Exception {

        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage("http://atealxt.appspot.com/guestbook");

        HtmlDivision div = page.getHtmlElementById("logo");
        Assert.assertNotNull(div);
        logger.info(div);

        HtmlAnchor anchor = page.getAnchorByName("top");
        Assert.assertNotNull(anchor);
        logger.info(anchor);

        List<?> divs = page.getByXPath("//div");
        Assert.assertTrue(divs.size() > 0);
        logger.info(divs);

        HtmlDivision returnToTop = (HtmlDivision) page.getByXPath("//div[@class='return-to-top']").get(0);
        Assert.assertNotNull(returnToTop);

        webClient.closeAllWindows();
    }
}
