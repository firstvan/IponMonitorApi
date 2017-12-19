package hu.unideb.inf.firstvan.services;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import hu.unideb.inf.firstvan.api.MonitorByLink;
import hu.unideb.inf.firstvan.model.Monitor;
import hu.unideb.inf.firstvan.processor.MonitorProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.io.IOException;

public class MonitorSearchByNameAndTypeService {

    private static String SEARCH_URI = "https://ipon.hu/search/shop-full/monitor%20";

    public MonitorSearchByNameAndTypeService() {
    }

    public Monitor doSearch(String name, String type) throws IOException {
        String url = SEARCH_URI + name.toLowerCase() + "%20" + type.toLowerCase();
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage(url);
        String content = page.getWebResponse().getContentAsString();
        Document doc = Jsoup.parse(content);

        if (doc.select("div#noresult-div").size() != 0) {
            System.out.println("Nincs találat");
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
        }

        Element link = doc.select("a.to-product").first();
        String product = link.attr("href");
        product = product.replace("/termek", "");

        MonitorByLinkService mls = new MonitorByLinkService();
        return mls.doSearch(product);
    }


}
