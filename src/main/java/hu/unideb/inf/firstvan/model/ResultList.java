package hu.unideb.inf.firstvan.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@javax.xml.bind.annotation.XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(
        name = "ResultList",
        propOrder = {
            "from",
            "numOfItem",
            "results"
        }
)
public class ResultList {

    @XmlAttribute(name = "uri")
    private String uri;

    @XmlElement
    private int from;

    @XmlElement
    private int numOfItem;

    @XmlElementWrapper(name = "results")
    @XmlElement(name = "result")
    private List<ResultItem> results;

    public ResultList() {
    }

    public ResultList(String uri, int from, int numOfItem, List<ResultItem> results) {
        this.uri = uri;
        this.from = from;
        this.numOfItem = numOfItem;
        this.results = results;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String url) {
        this.uri = url;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getNumOfItem() {
        return numOfItem;
    }

    public void setNumOfItem(int numOfItem) {
        this.numOfItem = numOfItem;
    }

    public List<ResultItem> getResults() {
        return results;
    }

    public void setResults(List<ResultItem> results) {
        this.results = results;
    }
}
