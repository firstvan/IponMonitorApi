package hu.unideb.inf.firstvan.api;

import hu.unideb.inf.firstvan.model.ResultList;
import hu.unideb.inf.firstvan.services.MonitorListByPageService;
import hu.unideb.inf.firstvan.services.MonitorListService;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.io.IOException;

public class MonitorListByPage extends ServerResource {
    @Get("json|xml")
    public ResultList represent() {
        String page = getAttribute("page");
        int tempPage = 0;

        if (page != null) {
            tempPage = Integer.parseInt(page);
        } else {
            MonitorListService mls = new MonitorListService();
            try {
                return mls.doSearch(null, null);
            } catch (IOException e) {
                throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
            }
        }

        MonitorListByPageService mlps = new MonitorListByPageService();
        try {
            return mlps.doSearch(tempPage);
        } catch (Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
        }
    }
}
