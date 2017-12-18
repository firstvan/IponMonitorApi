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

    public MonitorListProcessor() {
    }

    public ResultList parse(Document doc) throws IOException {
        ResultList resultList = null;
        try {
            resultList = doProcess(doc);
        } catch (Exception e) {
            throw new IOException("Malformed document");
        }

        return resultList;
    }

    private ResultList doProcess(Document doc) throws InvalidFormatException {
        ResultList resultList = new ResultList();

        Elements elements = doc.select("div.item");

        List<ResultItem> resultItems = new ArrayList<>();

        for (Element element : elements) {
            resultItems.add(parseElement(element));
        }

        resultList.setResults(resultItems);
        return resultList;
    }

    private ResultItem parseElement(Element element) throws InvalidFormatException {
        ResultItem resultItem = new ResultItem();
        resultItem.setUri(parseRemoteURI(element));
        resultItem.setName(parseName(element));
        resultItem.setPrice(parsePrice(element));
        return resultItem;
    }

    private String parseRemoteURI(Element element) {
        Element element1 = element.select("div.middle > a").first();
        return BASE_URI + element1.attr("href");
    }

    private String parseName(Element element) {
        Element element1 = element.select("div.middle > div.name > a").first();
        return this.parseNameFromDoc(element1);
    }

    private Price parsePrice(Element element) throws InvalidFormatException {
        Element element1 = element.select("p.webshop_price").first();
        return this.parsePriceFromDoc(element1);
    }
}
