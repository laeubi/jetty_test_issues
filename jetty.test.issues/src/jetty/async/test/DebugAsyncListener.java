/**
 * 
 */
package jetty.async.test;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

/**
 * @author Christoph Läubrich
 */
public class DebugAsyncListener implements AsyncListener {

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("DebugAsyncListener.onComplete()");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("DebugAsyncListener.onError()");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("DebugAsyncListener.onStartAsync()");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("DebugAsyncListener.onTimeout()");
    }

}
