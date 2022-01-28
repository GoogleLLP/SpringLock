package example.name.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lock.annotation.ReadLock;
import org.springframework.lock.annotation.Synchronized;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class BaseService {

    private static final Log LOGGER = LogFactory.getLog(BaseService.class);

//    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//
//    private final Lock readLock = lock.readLock();
//
//    private final Lock writeLock = lock.writeLock();

    @Synchronized
    public String testSynchronized() {
        String name = Thread.currentThread().getName();
        LOGGER.info(name + "开始执行");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(name + "执行结束");
        return "testSynchronized 执行结束";
    }

    @ReadLock
    public String testReadLock() {
        String name = Thread.currentThread().getName();
        LOGGER.info(name + "开始执行");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(name + "执行结束");
        return "testReadLock 执行结束";
    }
}