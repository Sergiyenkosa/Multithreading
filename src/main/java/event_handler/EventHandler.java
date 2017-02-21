package event_handler;

import java.util.concurrent.Callable;

/**
 * Created by s.sergienko on 20.02.2017.
 */
public interface EventHandler {
    void eventSubmit(Runnable runnable);

    void eventSubmit(Callable<?> callable);

    void shutdown();
}
