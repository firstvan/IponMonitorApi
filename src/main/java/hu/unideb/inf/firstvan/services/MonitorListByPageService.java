package hu.unideb.inf.firstvan.services;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import hu.unideb.inf.firstvan.model.ResultList;
import hu.unideb.inf.firstvan.processor.MonitorListProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MonitorListByPageService {
    private static final String SEARCH_URI = "https://ipon.hu/webshop/tag/megjelenitok/monitor";

    public MonitorListByPageService() {
    }

    public ResultList doSearch(int pageNo) throws IOException {
        WebClient webClient = new WebClient();
        String uri = SEARCH_URI + "/p" + pageNo;

        HtmlPage page = webClient.getPage(uri);
        String content = page.getWebResponse().getContentAsString();
        Document doc = Jsoup.parse(content);

        MonitorListProcessor monitorListProcessor = new MonitorListProcessor();
        ResultList resultList = monitorListProcessor.parse(doc, null);
        resultList.setNumOfItem(50);
        resultList.setFrom(pageNo * 50);
        return resultList;
    }

}
