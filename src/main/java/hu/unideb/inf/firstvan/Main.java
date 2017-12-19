package hu.unideb.inf.firstvan;

import hu.unideb.inf.firstvan.api.MonitorByLink;
import hu.unideb.inf.firstvan.api.MonitorList;
import hu.unideb.inf.firstvan.api.MonitorListByPage;
import hu.unideb.inf.firstvan.api.MonitorSearchByNameAndTypeImpl;
import hu.unideb.inf.firstvan.utils.IMRouter;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class Main extends Application {
    static {
        System.setProperty("org.restlet.engine.loggerFacadeClass", "org.restlet.ext.slf4j.Slf4jLoggerFacade");
    }

    private static final String PATH = "http://localhost";
    private static final int PORT = 8888;

    public static void main(String[] args) {
        Server server = new Server(Protocol.HTTP, 8888, new Main());
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restlet createInboundRoot() {
        IMRouter router = new IMRouter(getContext(), PATH, PORT);
        router.attach("/monitorSearch?name={name}&type={type}", MonitorSearchByNameAndTypeImpl.class);
        router.attach("/monitorList", MonitorList.class);
        router.attach("/monitorList?page={page}", MonitorListByPage.class);
        router.attach("/monitorList/{size}", MonitorList.class);
        router.attach("/monitorList/{size}?from={from}", MonitorList.class);
        router.attach("/webshop/product/{name}/{no}", MonitorByLink.class);
        return router.getRouter();
    }


}
