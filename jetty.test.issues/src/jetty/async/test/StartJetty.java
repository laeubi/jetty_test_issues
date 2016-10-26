package jetty.async.test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * @author Christoph Läubrich
 */
public class StartJetty {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9090);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(IncompleteWriteListenerServlet.class, "/iwl");
        handler.addServletWithMapping(TimeoutWriteListenerServlet.class, "/twl");
        handler.addServletWithMapping(BlockingTimeoutWriteListenerServlet.class, "/btwl");
        server.start();
        server.dumpStdErr();
        server.join();
    }
}
