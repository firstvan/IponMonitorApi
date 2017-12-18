package hu.unideb.inf.firstvan.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@javax.xml.bind.annotation.XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(
        name = "ResultItem",
        propOrder = {
                "name",
                "price",
                "localUri",
                "description"
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
    private String description;

    public ResultItem(String uri, String name, Price price, String localUri, String description) {
        this.uri = uri;
        this.name = name;
        this.price = price;
        this.localUri = localUri;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
