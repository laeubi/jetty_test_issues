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
        System.out.println("[" + Thread.currentThread().getName() + "] AsyncListener.onComplete()");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("[" + Thread.currentThread().getName() + "] AsyncListener.onError()");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("[" + Thread.currentThread().getName() + "] AsyncListener.onStartAsync()");
        event.getAsyncContext().addListener(this);
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("[" + Thread.currentThread().getName() + "] AsyncListener.onTimeout()");
    }

}
