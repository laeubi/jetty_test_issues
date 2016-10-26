/**
 * 
 */
package jetty.async.test;

import java.io.IOException;

import javax.servlet.WriteListener;

/**
 * @author Christoph Läubrich
 */
public class DebugWriteListener implements WriteListener {

    @Override
    public void onError(Throwable t) {
        System.out.println("[" + Thread.currentThread().getName() + "] WriteListener.onError(" + t + ")");
    }

    @Override
    public void onWritePossible() throws IOException {
        System.out.println("[" + Thread.currentThread().getName() + "] WriteListener.onWritePossible()");
    }

}
