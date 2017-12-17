package hu.unideb.inf.firstvan.api;

import hu.unideb.inf.firstvan.model.Monitor;
import hu.unideb.inf.firstvan.services.MonitorSearchByNameAndTypeService;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.io.IOException;

public class MonitorSearchByNameAndTypeImpl extends ServerResource {

    @Get("json|xml")
    public Monitor represent() {
        String name = getAttribute("name");
        String type = getAttribute("type");
        MonitorSearchByNameAndTypeService msnts = new MonitorSearchByNameAndTypeService();
        try {
            return msnts.doSearch(name, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
    }
}
