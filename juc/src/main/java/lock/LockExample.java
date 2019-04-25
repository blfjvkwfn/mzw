package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jonathan Meng
 * @date 23/04/2019
 */
public class LockExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        if (lock.tryLock()) {
            try {
                // do something
            } finally {
                lock.unlock();
            }
        }
    }
}
