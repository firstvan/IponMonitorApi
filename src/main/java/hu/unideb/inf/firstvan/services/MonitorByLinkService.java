package hu.unideb.inf.firstvan.services;

import hu.unideb.inf.firstvan.model.Monitor;
import hu.unideb.inf.firstvan.processor.MonitorProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MonitorByLinkService {
    private static String ITEM_URI = "https://ipon.hu";

    public MonitorByLinkService() {
    }

    public Monitor doSearch(String href) throws IOException {
        String url = ITEM_URI + href;
        Document doc = Jsoup.connect(url).userAgent("Mozzila").get();
        MonitorProcessor mp = new MonitorProcessor();
        return mp.parse(doc, url);
    }
}
