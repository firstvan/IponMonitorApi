package hu.unideb.inf.firstvan.processor;

import com.sun.media.sound.InvalidFormatException;
import hu.unideb.inf.firstvan.model.Price;
import hu.unideb.inf.firstvan.model.ResultItem;
import hu.unideb.inf.firstvan.model.ResultList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonitorListProcessor extends BaseMonitorProcessor {

    private static final String BASE_URI = "https://ipon.hu";
    private static final String LOCAL_URI = "http://localhost:8888";
    private Element element;
    private Document doc;

    public MonitorListProcessor() {
    }

    public ResultList parse(Document doc, String from) throws IOException {
        ResultList resultList = null;
        try {
            this.doc = doc;
            resultList = doProcess(from);
        } catch (Exception e) {
            throw new IOException("Malformed document");
        }

        return resultList;
    }

    private ResultList doProcess(String from) throws InvalidFormatException {
        ResultList resultList = new ResultList();

        Elements elements = this.doc.select("div.item");

        List<ResultItem> resultItems = new ArrayList<>();

        int tempFrom = 0;

        if (from != null) {
            tempFrom = Integer.parseInt(from);
        }

        int counter = 0;
        for (Element element : elements) {
            if (counter >= tempFrom) {
                this.element = element;
                resultItems.add(parseElement());
            }
            counter++;
        }

        resultList.setResults(resultItems);
        return resultList;
    }

    private ResultItem parseElement() throws InvalidFormatException {
        ResultItem resultItem = new ResultItem();
        resultItem.setUri(parseRemoteURI());
        resultItem.setName(parseName());
        resultItem.setPrice(parsePrice());
        resultItem.setShortDescription(parseDescription());
        resultItem.setLocalUri(parseLocalUri());
        return resultItem;
    }

    private String parseRemoteURI() {
        Element element1 = this.element.select("div.middle > a").first();
        return BASE_URI + element1.attr("href");
    }

    private String parseName() {
        Element element1 = this.element.select("div.middle > div.name > a").first();
        return this.parseNameFromDoc(element1);
    }

    private Price parsePrice() throws InvalidFormatException {
        Element element1 = this.element.select("p.webshop_price").first();
        return this.parsePriceFromDoc(element1);
    }

    private String parseDescription() {
        Element element1 = this.element.select("div.description > a").first();
        return this.parseDescriptionFromDoc(element1);
    }

    private String parseLocalUri() {
        Element element1 = this.element.select("a.to-product").first();
        return LOCAL_URI + element1.attr("href");
    }
}
