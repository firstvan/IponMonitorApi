package hu.unideb.inf.firstvan.model;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RefreshRate {

    @XmlValue
    private BigDecimal value;

    @XmlAttribute(name = "unit", required = true)
    @XmlSchemaType(name = "token")
    private String unit;

    public RefreshRate() {
    }

    public RefreshRate(BigDecimal value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}