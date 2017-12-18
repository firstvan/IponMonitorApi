package hu.unideb.inf.firstvan.services;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import hu.unideb.inf.firstvan.model.ResultList;
import hu.unideb.inf.firstvan.processor.MonitorListProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MonitorListService {

    private static String SEARCH_URI = "https://ipon.hu/webshop/tag/megjelenitok/monitor";

    public MonitorListService() {
    }

    public ResultList doSearch() throws IOException {
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage(SEARCH_URI);
        String content = page.getWebResponse().getContentAsString();
        Document doc = Jsoup.parse(content);

        MonitorListProcessor monitorListProcessor = new MonitorListProcessor();

        return monitorListProcessor.parse(doc);
    }

}