package zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKUtils {

    private static volatile ZooKeeper zooKeeper = null;

    private ZKUtils() {
    }

    public static ZooKeeper getZK(String address, int timeout, final Watcher watcher) throws IOException, InterruptedException {
        if (zooKeeper != null) {
            return zooKeeper;
        }
        synchronized (ZKUtils.class) {
            if (zooKeeper != null) {
                return zooKeeper;
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            address = ((address != null) && (address.length() > 0)) ? address : "192.168.163.184:2181/distribute";
            zooKeeper = new ZooKeeper(address, timeout, watchedEvent -> {
                if (watcher != null) {
                    watcher.process(watchedEvent);
                }
                switch (watchedEvent.getState()) {
                    case Disconnected:
                        break;
                    case SyncConnected:
                        countDownLatch.countDown();
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                    case Closed:
                        break;
                }
            });
            countDownLatch.await();
        }
        return zooKeeper;
    }
}