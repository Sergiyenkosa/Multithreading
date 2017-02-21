package read_write_data;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by s.sergienko on 21.02.2017.
 */
public class ThreadSafeData {
    private static volatile String data;

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public static String readData() {
        lock.readLock().lock();

        String s = data;

        lock.readLock().unlock();

        return s;
    }

    public static void writeData(String data) {
        lock.writeLock().lock();

        ThreadSafeData.data = data;

        lock.writeLock().unlock();
    }
}
