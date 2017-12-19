package hu.unideb.inf.firstvan.api;

import hu.unideb.inf.firstvan.model.Monitor;
import hu.unideb.inf.firstvan.services.MonitorByLinkService;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.io.IOException;

public class MonitorByLink extends ServerResource {

    private final static String REMOTE_ITEM_URI = "/webshop/product";

    @Get("json|xml")
    public Monitor represent() {
        String nameAndType = getAttribute("name");
        String no = getAttribute("no");

        MonitorByLinkService monitorByLinkService = new MonitorByLinkService();

        try {
            return monitorByLinkService.doSearch(getLink(nameAndType, no));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);

    }

    private String getLink(String nameAndType, String no) {
        return REMOTE_ITEM_URI + "/" + nameAndType + "/" + no;
    }
}
