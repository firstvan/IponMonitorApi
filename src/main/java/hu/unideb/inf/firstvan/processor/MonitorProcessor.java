package hu.unideb.inf.firstvan.processor;

import hu.unideb.inf.firstvan.model.*;

import hu.unideb.inf.firstvan.utils.NumberParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UnknownFormatConversionException;
import java.util.stream.Collectors;


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
        parsePrice(doc, monitor);
        setDescription(doc, monitor);
        setSpecification(doc, monitor);
        setPictureUrls(doc, monitor);
        setDeleveryDate(doc, monitor);
        return monitor;
    }

    private void setMonitorName(Document doc, Monitor monitor) {
        String name = parseName(doc);
        monitor.setName(name);
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

    private void setPictureUrls(Document doc, Monitor monitor) {
        Elements imageElements = doc.select("div.swiper-slide > img");
        List<Image> imgs =
                imageElements.stream().map(item -> new Image(item.attr("src"))).collect(Collectors.toList());
        monitor.setImages(imgs);
    }

    private void setDeleveryDate(Document doc, Monitor monitor) {
        Element date = doc.select("h3:contains(Átvétel)").first().parent()
                .select("div > p:contains(házhozszállításnál) > strong").first();
        String dateString = date.text().split(" ")[0];
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy.MM.dd."));
        monitor.setDeleveryTime(localDate);
    }

    private void setMonitorSpecificationByPropertiesArray(Monitor monitor, String[] properties) {
        switch (properties[0]) {
            case "képernyő átló":
                ScreenSize screenSize = getScreenSize(properties[1]);
                monitor.setScreenSize(screenSize);
                break;
            case "felbontás":
                monitor.setResolution(properties[1]);
                break;
            case "válaszidő":
                ResponseTime responseTime = getResponseTime(properties[1]);
                monitor.setResponseTime(responseTime);
                break;
            case "képfrissítési frekvencia":
                RefreshRate refreshRate = getRefreshRate(properties[1]);
                monitor.setRefreshRate(refreshRate);
                break;
            case "képarány":
                monitor.setAspectRatio(properties[1]);
                break;
            case "szín":
                monitor.setColor(properties[1]);
                break;
            case "kontrasztarány":
                monitor.setContrast(properties[1]);
                break;
            case "fényerő":
                Brightness brightness = getBrightness(properties[1]);
                monitor.setBrightness(brightness);
                break;
        }
    }

    private RefreshRate getRefreshRate(String property) {
        RefreshRate refreshRate = new RefreshRate();
        String[] texts = property.trim().split(" ");
        checkFormatIsValid(texts);
        refreshRate.setValue(BigDecimal.valueOf(Long.valueOf(texts[0])));
        refreshRate.setUnit(texts[1]);
        return refreshRate;
    }

    private ResponseTime getResponseTime(String property) {
        ResponseTime responseTime = new ResponseTime();
        String[] texts = property.trim().split(" ");
        checkFormatIsValid(texts);
        responseTime.setValue(BigDecimal.valueOf(Long.valueOf(texts[0])));
        responseTime.setUnit(texts[1]);
        return responseTime;
    }

    private Brightness getBrightness(String property) {
        Brightness brightness = new Brightness();
        brightness.setValue(BigDecimal.valueOf(Long.valueOf(property)));
        brightness.setUnit("cd/m2");
        return brightness;
    }

    private void checkFormatIsValid(String[] texts) {
        if (texts.length != 2) {
            throw new UnknownFormatConversionException("Invalid format") ;
        }
    }

    private ScreenSize getScreenSize(String property) {
        ScreenSize screenSize = new ScreenSize();
        screenSize.setValue(NumberParser.getIntNumberFromString(property));
        // TODO: ha több mértékegység megjelenik itt lekezelni.
        screenSize.setUnit("inch");
        return screenSize;
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
