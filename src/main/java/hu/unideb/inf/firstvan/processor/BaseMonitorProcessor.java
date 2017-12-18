package hu.unideb.inf.firstvan.processor;

import com.sun.media.sound.InvalidFormatException;
import hu.unideb.inf.firstvan.model.Price;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;

public class BaseMonitorProcessor {

    public BaseMonitorProcessor() {
    }

    protected Price parsePriceFromDoc(Element element) throws InvalidFormatException {
        String[] priceInfo = element.text().split(" ");
        if(priceInfo.length >= 2) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < priceInfo.length - 1; i++) {
                sb.append(priceInfo[i]);
            }

            return getPrice(priceInfo[priceInfo.length - 1], sb);
        }

        throw new InvalidFormatException();
    }

    protected String parseDescriptionFromDoc(Element element) {
        return element.text().trim().replace('"', '\"');
    }

    protected String parseNameFromDoc(Element element) {
        return element.text().trim();
    }

    private Price getPrice(String currency, StringBuilder sb) {
        Price p = new Price();
        p.setValue(BigDecimal.valueOf(Long.valueOf(sb.toString())));
        p.setCurrency(currency);
        return p;
    }
}
