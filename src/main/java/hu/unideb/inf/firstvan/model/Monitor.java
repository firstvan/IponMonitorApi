package hu.unideb.inf.firstvan.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@javax.xml.bind.annotation.XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(
        name = "Monitor",
        propOrder = {
            "name",
            "price",
            "description",
            "screenSize",
            "resolution",
            "responseTime",
            "refreshRate",
            "aspectRatio",
            "color"
        }
)
public class Monitor {

    @XmlAttribute(required = true)
    private String uri;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private Price price;

    @XmlElement
    private String description;

    @XmlElement
    private String  screenSize;

    @XmlElement
    private String resolution;

    @XmlElement
    private String responseTime;

    @XmlElement
    private String refreshRate;

    @XmlElement
    private String aspectRatio;

    @XmlElement
    private String color;

    public Monitor() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(String refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
