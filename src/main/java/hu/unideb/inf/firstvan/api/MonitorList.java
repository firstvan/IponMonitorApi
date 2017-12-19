package hu.unideb.inf.firstvan.api;

import hu.unideb.inf.firstvan.model.ResultList;
import hu.unideb.inf.firstvan.services.MonitorListService;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class MonitorList extends ServerResource {

    @Get("json|xml")
    public ResultList represent() {
        String size = getAttribute("size");
        String from = getAttribute("from");

        MonitorListService monitorListService = new MonitorListService();

        try {
            return monitorListService.doSearch(size, from);
        } catch (Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
        }
    }
}
