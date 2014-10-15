package htmlunitdemo.test.getstarted;

import htmlunitdemo.test.TestBase;

import java.net.URL;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequestSettings;
import com.gargoylesoftware.htmlunit.WebResponse;

public class JsonTest extends TestBase {

    @Override
    public void excute() throws Exception {
        String url = //
        "http://203.86.54.32/CYQ//mapwork.do?act=getmapschfindtable&type=0&area=10049&cur=1";
        url += "&tmp=" + (int) Math.floor(Math.random() * 100);
        url += "&key=" + URLEncoder.encode(URLEncoder.encode("小", "utf-8"), "utf-8");

        logger.debug(url);

        WebClient webClient = new WebClient();

        WebRequestSettings setting = new WebRequestSettings(new URL(url));
        WebResponse response = webClient.loadWebResponse(setting);
        response.getContentAsString();

        String json = response.getContentAsString();
        logger.debug(response.getContentAsString());
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object bean = JSONObject.toBean(jsonObject);
        logger.debug(bean);

        webClient.closeAllWindows();
    }

}
