package hu.unideb.inf.firstvan.processor;

import hu.unideb.inf.firstvan.model.Monitor;

import hu.unideb.inf.firstvan.model.Price;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;


public class MonitorProcessor {

    public MonitorProcessor() {
    }

    public Monitor parse(Document doc, String uri) throws IOException{
        Monitor monitor = null;
        try {
            monitor = doProcess(doc);
            monitor.setUri(uri);
        } catch (Exception e) {
            throw new IOException("Malformed document");
        }

        return monitor;
    }

    private Monitor doProcess(Document doc) {
        Monitor monitor = new Monitor();
        setMonitorName(doc, monitor);
        setMonitorPrice(doc, monitor);
        setDescription(doc, monitor);
        setSpecification(doc, monitor);
        return monitor;
    }

    private void setMonitorName(Document doc, Monitor monitor) {
        String name = parseName(doc);
        monitor.setName(name);
    }

    private void setMonitorPrice(Document doc, Monitor monitor) {
        parsePrice(doc, monitor);
    }

    private void setDescription(Document doc, Monitor monitor) {
        String description = parseDescription(doc);
        monitor.setDescription(description);
    }

    private void setSpecification(Document doc, Monitor monitor) {
        Elements elements = getUlElements(doc);

        if (elements == null) {
            return;
        }

        for (Element e : elements) {
            String[] properties = e.text().toLowerCase().trim().split(": ");
            setMonitorSpecificationByPropertiesArray(monitor, properties);
        }
    }

    private void setMonitorSpecificationByPropertiesArray(Monitor monitor, String[] properties) {
        switch (properties[0]) {
            case "képernyő átló":
                monitor.setScreenSize(properties[1]);
                break;
            case "felbontás":
                monitor.setResolution(properties[1]);
                break;
            case "válaszidő":
                monitor.setResponseTime(properties[1]);
                break;
            case "képfrissítési frekvencia":
                monitor.setRefreshRate(properties[1]);
                break;
            case "képarány":
                monitor.setAspectRatio(properties[1]);
                break;
            case "szín":
                monitor.setColor(properties[1]);
                break;
        }
    }

    private String parseName(Document doc) {
        Element element = doc.select(".product-name").first();
        return element.text();
    }

    private void parsePrice(Document doc, Monitor monitor) {
        Element element = doc.select(".lg.purple").first();
        String[] priceInfo = element.text().split(" ");
        if(priceInfo.length >= 2) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < priceInfo.length - 1; i++) {
                sb.append(priceInfo[i]);
            }

            Price p = getPrice(priceInfo[priceInfo.length - 1], sb);
            monitor.setPrice(p);
        }
    }

    private Price getPrice(String currency, StringBuilder sb) {
        Price p = new Price();
        p.setValue(BigDecimal.valueOf(Long.valueOf(sb.toString())));
        p.setCurrency(currency);
        return p;
    }

    private String parseDescription(Document doc) {
        Elements elements = doc.select("h3");
        for(Element e : elements) {
            String header = e.text().trim().toLowerCase();
            if(header.equals("leírás")) {
                return e.parent().select("div > p").first().text().replace('"', '\"');
            }
        }
        return "";
    }

    private Elements getUlElements(Document doc) {
        Elements elements = doc.select(".heading-title");
        Optional<Element> element = elements.stream()
                .filter(e -> e.text().trim().toLowerCase().equals("specifikáció")).findFirst();

        if (element.isPresent()) {
            return element.get().parent().select("div > ul > li");
        }

        return null;
    }

}
