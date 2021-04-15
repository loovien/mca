package zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import zookeeper.locks.DistributeLock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ZKper {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // connectZK();
        // demo();
        // zkUtil();

        ZooKeeper zk = ZKUtils.getZK("192.168.163.184:2181/luowen", 3000, null);

        String lockIdentity = "/locks";

        for (int corsor = 0; corsor < 2; corsor++) {
            new Thread(() -> {
                DistributeLock distributeLock = new DistributeLock(zk);
                distributeLock.tryLock(lockIdentity);
                log.info(Thread.currentThread().getName() + " do working.....");
                distributeLock.unLock(lockIdentity);
            }, "work-thread-" + corsor).start();
        }
        System.in.read();
    }

    private static void demo() throws IOException, InterruptedException, KeeperException {

        ZooKeeper zk = ZKUtils.getZK("192.168.183.184:2181", 3000, null);

        zk.exists("/luowen", watchedEvent -> {
            log.info("******************************** yyy ************************");
        });
        zk.getData("/luowen", watchedEvent -> {
            log.info("==================xxx=========================");
            log.info("==================xxx=========================");

        }, (i, s, o, bytes, stat) -> {
            log.info("==================yyy=========================");
            log.info(i + " + " + s + " + " + o + " + " + (new String(bytes)) + " + " + stat);
            log.info("==================yyy=========================");

        }, "");

        TimeUnit.SECONDS.sleep(10);
    }

    private static void zkUtil() throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = ZKUtils.getZK("192.168.163.184:2181", 2000, null);
        DataListener dataListener = new DataListener(zk);

        dataListener.dataWatch("/luowen");

        while (true) {
            String result = dataListener.getResult();
            log.info("get result from zookeeper: " + result);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static void connectZK() throws IOException, InterruptedException, KeeperException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("192.168.163.184:2181", 3000, watchedEvent -> {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                countDownLatch.countDown();
                return;
            }
            throw new RuntimeException("not connected zookeeper server");
        });
        countDownLatch.await();
//        String createResult = zooKeeper.create("/luowen", "luowen".getBytes(StandardCharsets.UTF_8),
//                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        log.info(createResult);
        log.info("================= state =====================");
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/luowen", null, stat);
        log.info(new String(data));
    }
}
