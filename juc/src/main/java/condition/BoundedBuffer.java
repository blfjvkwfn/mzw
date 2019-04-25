package condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jonathan Meng
 * @date 23/04/2019
 */
public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[10];
    int putIndex;
    int takeIndex;
    int count;

    public void put(Object obj) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.wait();
            }
            items[putIndex] = obj;
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
          lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.wait();
            }
            Object obj = items[takeIndex];
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            --count;
            notFull.signal();
            return obj;
        } finally {
            lock.unlock();
        }
    }
}
