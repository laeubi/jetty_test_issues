/**
 * 
 */
package jetty.async.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Issues an async write with contentlength != bytes writte before
 * 
 * @author Christoph Läubrich
 */
public class BlockingTimeoutWriteListenerServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 3431896464670815605L;
    /**
     * 
     */
    private static final int  WRITE_CHARS      = 10000;

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request: " + req.getRequestURI());
        final AsyncContext async = req.startAsync();
        async.setTimeout(TimeUnit.SECONDS.toMillis(1));
        async.addListener(new DebugAsyncListener() {
            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                super.onTimeout(event);
                async.complete();
            }
        });
        final byte[] responsebytes = new byte[WRITE_CHARS - 10];
        for (int i = 0; i < responsebytes.length; i++) {
            if (i > 0 && i % 100 == 0) {
                responsebytes[i] = '\n';
            } else {
                responsebytes[i] = (byte) (48 + (Math.random() * 74));
            }
        }
        final ServletOutputStream stream = resp.getOutputStream();
        stream.setWriteListener(new DebugWriteListener() {

            @Override
            public void onWritePossible() throws IOException {
                super.onWritePossible();
                try {
                    TimeUnit.DAYS.sleep(1);
                } catch (InterruptedException e) {
                }
            }

        });
    }
}
