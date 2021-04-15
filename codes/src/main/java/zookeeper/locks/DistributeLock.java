package zookeeper.locks;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper distribute lock implement,
 * <p>
 * two problem not resolve:
 * 1. ReEntrance lock not implement.
 * 2. unlock too fast made flower register listener not working
 */
@Slf4j
public class DistributeLock implements Watcher, AsyncCallback.StringCallback, AsyncCallback.Children2Callback, AsyncCallback.StatCallback {

    private final ZooKeeper zooKeeper;

    private String sequenceLockName;

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    public DistributeLock(ZooKeeper zooKeeper) {

        this.zooKeeper = zooKeeper;
    }

    public void start() throws IOException {
    }

    public void tryLock(String lockIdentity) {
        try { // create lock identity
            zooKeeper.create(lockIdentity, "".getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, lockIdentity);
            countDownLatch.await(); // block current thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unLock(String lockIdentity) {
        try {
            log.info("===================== delete node ========================");
            zooKeeper.delete(sequenceLockName, -1);
            log.info(Thread.currentThread().getName() + " delete node: " + sequenceLockName);
            log.info("===================== delete node ========================");
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if (path == null || path.length() <= 0) {
            return;
        }
        sequenceLockName = name;
        zooKeeper.getChildren("/", false, this, sequenceLockName);


    }

    /**
     * listen child
     *
     * @param rc       resultCode code of operation
     * @param path     path of parent
     * @param ctx      context
     * @param children children nodes
     * @param stat     operation stat
     */
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        Collections.sort(children);
        log.info("________________________________________________________________");
        log.info("current lock: " + sequenceLockName);
        log.info("rc:  " + rc + " path: " + path + " object: " + ctx + " list: " + children + " stat: " + stat);
        log.info("________________________________________________________________");

        String nameInChildren = sequenceLockName.substring(1);
        log.info("**************8 " + nameInChildren + " = " + children.get(0));
        if (nameInChildren.equals(children.get(0))) {
            log.info("===============================");
            try {
                zooKeeper.setData(sequenceLockName, sequenceLockName.getBytes(StandardCharsets.UTF_8), stat.getVersion());
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            return;
        }
        int index = children.indexOf(nameInChildren);
        // zooKeeper.exists(children.get(index - 1), true, this, sequenceLockName);
        // add previous node listener
        zooKeeper.exists("/" + children.get(index - 1), this, this, sequenceLockName);
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                zooKeeper.getChildren("/", false, this, sequenceLockName);
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
            case PersistentWatchRemoved:
                break;
        }
    }
}
