package hu.unideb.inf.firstvan.model;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ScreenSize {

    @XmlValue
    private int value;

    @XmlAttribute(name = "unit", required = true)
    @XmlSchemaType(name = "token")
    private String unit;

    public ScreenSize() {
    }

    public ScreenSize(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
