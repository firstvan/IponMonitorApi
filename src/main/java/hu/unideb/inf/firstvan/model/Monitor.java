package hu.unideb.inf.firstvan.model;

import hu.unideb.inf.firstvan.utils.LocalDateAdapter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

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
            "color",
            "contrast",
            "brightness",
            "images",
            "deleveryTime"
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
    private ScreenSize screenSize;

    @XmlElement
    private String resolution;

    @XmlElement
    private ResponseTime responseTime;

    @XmlElement
    private RefreshRate refreshRate;

    @XmlElement
    private String aspectRatio;

    @XmlElement
    private String color;

    @XmlElement
    private String contrast;

    @XmlElement
    private Brightness brightness;

    @XmlElement(name = "image")
    @XmlElementWrapper(name="images")
    private List<Image> images;

    @XmlElement(name = "deleveryTime")
    @XmlSchemaType(name = "date")
    @javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate deleveryTime;

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

    public ScreenSize getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(ScreenSize screenSize) {
        this.screenSize = screenSize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public ResponseTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(ResponseTime responseTime) {
        this.responseTime = responseTime;
    }

    public RefreshRate getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(RefreshRate refreshRate) {
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

    public String getContrast() {
        return contrast;
    }

    public void setContrast(String contrast) {
        this.contrast = contrast;
    }

    public Brightness getBrightness() {
        return brightness;
    }

    public void setBrightness(Brightness brightness) {
        this.brightness = brightness;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public LocalDate getDeleveryTime() {
        return deleveryTime;
    }

    public void setDeleveryTime(LocalDate deleveryTime) {
        this.deleveryTime = deleveryTime;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
