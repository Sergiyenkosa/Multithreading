package event_handler.events_handlers_impl;

import event_handler.EventHandler;

import java.util.concurrent.*;

/**
 * Created by s.sergienko on 20.02.2017.
 */
public class EventHandlerImpl implements EventHandler{
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public void eventSubmit(Runnable runnable) {
        executorService.submit(runnable);
    }

    @Override
    public void eventSubmit(Callable<?> callable) {
        executorService.submit(callable);
    }

    @Override
    public void shutdown() {
        executorService.shutdown();
    }
}
