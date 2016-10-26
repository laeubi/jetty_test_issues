/**
 * 
 */
package jetty.async.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Issues an async write with contentlength != bytes writte before
 * 
 * @author Christoph Läubrich
 */
public class DispatchFromTimeoutServlet extends HttpServlet {

    /**
     * 
     */
    private static final String TIMEOUT_ATTRIBUTE = "Timeout";
    private static final long   serialVersionUID  = 3431896464670815605L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object timeoutMarker = req.getAttribute(TIMEOUT_ATTRIBUTE);
        System.out.println("Request: " + req.getRequestURI() + " Timeout: " + timeoutMarker + " Thread: " + Thread.currentThread().getName());
        if (timeoutMarker == null) {
            final AsyncContext async = req.startAsync();
            async.setTimeout(TimeUnit.SECONDS.toMillis(5));
            async.addListener(new DebugAsyncListener() {
                @Override
                public void onTimeout(javax.servlet.AsyncEvent event) throws IOException {
                    System.out.println("Timeout occured in Listener @ Thread: " + Thread.currentThread().getName());
                    event.getAsyncContext().getRequest().setAttribute(TIMEOUT_ATTRIBUTE, true);
                    event.getAsyncContext().dispatch();
                };
            });
        } else {
            System.out.println("Timeout occured in Thread: " + Thread.currentThread().getName());
        }
    }
}
