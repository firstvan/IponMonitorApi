package hu.unideb.inf.firstvan.services;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import hu.unideb.inf.firstvan.model.ResultList;
import hu.unideb.inf.firstvan.processor.MonitorListProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MonitorListService {

    private static final String SEARCH_URI = "https://ipon.hu/webshop/tag/megjelenitok/monitor";

    public MonitorListService() {
    }

    public ResultList doSearch(String size, String from) throws IOException {
        WebClient webClient = new WebClient();
        String uri = SEARCH_URI;
        int tempSize = 50;

        if (size != null) {
            tempSize = Integer.valueOf(size);
            uri += "?listlimit=" + tempSize;
        }

        HtmlPage page = webClient.getPage(uri);
        String content = page.getWebResponse().getContentAsString();
        Document doc = Jsoup.parse(content);

        MonitorListProcessor monitorListProcessor = new MonitorListProcessor();
        ResultList resultList = monitorListProcessor.parse(doc, from);
        resultList.setNumOfItem(tempSize);
        if (from != null) {
            resultList.setFrom(Integer.parseInt(from));
        }
        return resultList;
    }

}