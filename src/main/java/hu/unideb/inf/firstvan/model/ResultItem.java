package hu.unideb.inf.firstvan.model;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        propOrder = {
                "name",
                "price",
                "localUri",
                "shortDescription"
        }
)
public class ResultItem {

    @XmlAttribute(required = true)
    private String uri;

    @XmlElement(required = true)
    private String name;

    @XmlElement
    private Price price;

    @XmlElement
    private String localUri;

    @XmlElement
    private String shortDescription;

    public ResultItem(String uri, String name, Price price, String localUri, String shortDescription) {
        this.uri = uri;
        this.name = name;
        this.price = price;
        this.localUri = localUri;
        this.shortDescription = shortDescription;
    }

    public ResultItem() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getLocalUri() {
        return localUri;
    }

    public void setLocalUri(String localUri) {
        this.localUri = localUri;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
